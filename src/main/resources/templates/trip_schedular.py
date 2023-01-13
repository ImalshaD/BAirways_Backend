import datetime
import random
bb="insert into trip(scheduled_date,departure,arrival,route_id,plane_id,status_id) values ('{date}','{dep}','{arri}',{r},{p},1);"
start_date = datetime.date(2023, 1, 16)
end_date   = datetime.date(2023, 2, 16)
num_days   = (end_date - start_date).days
for i in range(40):
    rand_days   = random.randint(1, num_days)
    random_date = start_date + datetime.timedelta(days=rand_days)
    route = random.randint(1,91)
    plane_id = random.randint(1,9)
    start_hour = random.randint(0,24)
    start_min = random.randint(0,60)
    gap = random.randint(1,2)
    gap_min = random.randint(15,60)
    end_min = (start_min+gap_min)%60
    end_hour = start_hour+gap
    dep = "{start_hour}-{start_min}-00".format(start_hour=start_hour,start_min=start_min)
    arr = "{end_hour}-{end_min}-00".format(end_hour=end_hour,end_min=end_min)
    print(bb.format(date=random_date,dep=dep,arri=arr,r=route,p=plane_id))
