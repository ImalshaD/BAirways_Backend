import random


x="insert into prices(route_id,class_id,price) values ('{route_id}','{class_id}','{price}');"

for i in range(1,91):
    price_first = random.randint(600,1200)
    for j in range(1,4):
        print(x.format( route_id=i,class_id=j,price=price_first))
        price_first-=100