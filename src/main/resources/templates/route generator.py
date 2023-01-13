x='insert into route(from_airport,to_airport) values("{from1}","{to}");'
listairpoers = ["BIA","HRI","DEL",'CGK','DPS','BOM','MAA','BKK','DMK','SIN']

for i in range(len(listairpoers)):
    for j in range(len(listairpoers)):
        if (i==j):
            continue
        print(x.format( from1=listairpoers[i],to=listairpoers[j]))
