package com.Airways.BAirways.Database;

import com.Airways.BAirways.Utility.Annotations.*;
import com.Airways.BAirways.Utility.Exeptions.DataTypeExeption;
import com.Airways.BAirways.Utility.QueryHelper.Query.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Initializer {
    private static boolean isBuild=false;
    private static CreateQueryGenerator createQueryGenerator = new CreateQueryGeneratorTypeII();
    private static ArrayList<String> createQueries = new ArrayList<String>();
    private static ArrayList<Trigger> triggers = new ArrayList<Trigger>();
    private static ArrayList<Event> events = new ArrayList<>();
    private static ArrayList<ForeignKeyQuery> foreignkeyQueries = new ArrayList<ForeignKeyQuery>();
    private static ArrayList<IndexQuery> indexQueries = new ArrayList<IndexQuery>();
    public static void build() throws DataTypeExeption, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (!isBuild){
            buildDB();
        }
        isBuild=true;
    }
    public static void rebuild() throws DataTypeExeption, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (isBuild){
            isBuild=false;
            createQueryGenerator.reset();
            createQueries = new ArrayList<String>();
            foreignkeyQueries = new ArrayList<ForeignKeyQuery>();
            indexQueries = new ArrayList<IndexQuery>();
            triggers = new ArrayList<Trigger>();
            events = new ArrayList<Event>();
            buildDB();
        }
    }
    private static void buildDB() throws ClassNotFoundException, NoSuchMethodException, DataTypeExeption, InvocationTargetException, IllegalAccessException {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(MyTable.class));
        Class clss;
        for (BeanDefinition bd : scanner.findCandidateComponents("com.Airways.BAirways.Entity")){
            createQueryGenerator.reset();
            createQueryGenerator.setTableName(Class.forName(bd.getBeanClassName()).getSimpleName());
            IndexQueryGenerator indexQueryGenerator = createQueryGenerator.getIndexQueryGeneratorForTable();
            clss = Class.forName(bd.getBeanClassName());
            for (Field coloumn : clss.getDeclaredFields()){
                if (coloumn.isAnnotationPresent(MyColoumn.class)){
                    MyColoumn myColoumn = coloumn.getAnnotation(MyColoumn.class);
                    if (coloumn.isAnnotationPresent(MyPrimaryKey.class)){
                        MyPrimaryKey myPrimaryKey = coloumn.getAnnotation(MyPrimaryKey.class);
                        createQueryGenerator.setPrimaryKey(coloumn.getName(),coloumn.getType(),myPrimaryKey.autoIncrement());
                    }else if (coloumn.isAnnotationPresent(MyForiegnKey.class)){
                        MyForiegnKey myForiegnKey = coloumn.getAnnotation(MyForiegnKey.class);
                        createQueryGenerator.setOtherColoumn(coloumn.getName(),coloumn.getType(),myColoumn.unique());
                        foreignkeyQueries.add(createQueryGenerator.getForeignKeyQuery(coloumn.getName(),myForiegnKey.table().getSimpleName(),myForiegnKey.coloumn()));
                    }else{
                        createQueryGenerator.setOtherColoumn(coloumn.getName(),coloumn.getType(),myColoumn.unique());
                    }
                    if (coloumn.isAnnotationPresent(MyIndex.class)){
                        MyIndex myIndex = coloumn.getAnnotation(MyIndex.class);
                        indexQueryGenerator.setIndex(myIndex.indexName(), coloumn.getName());
                    }
                }
            }
            for (Method method :clss.getMethods()){
                if (method.isAnnotationPresent(MyTrigger.class)){
                    Object result=method.invoke(null);
                    triggers.add((Trigger) result);
                } else if (method.isAnnotationPresent(MyEvent.class)) {
                    Object result=method.invoke(null);
                    events.add((Event) result);
                }
            }
            createQueries.add(createQueryGenerator.getQuery());
            for (IndexQuery s : indexQueryGenerator.getIndexQueryList()){
                indexQueries.add(s);
            }
        }
    }
    public static ArrayList<String> getCreateQueries() {
        return createQueries;
    }

    public static ArrayList<ForeignKeyQuery> getForeignkeyQueries() {
        return foreignkeyQueries;
    }

    public static ArrayList<IndexQuery> getIndexQueries() {
        return indexQueries;
    }

    public static ArrayList<Trigger> getTriggers() {
        return triggers;
    }
    public static ArrayList<Event> getEvents() { return  events;}
}
