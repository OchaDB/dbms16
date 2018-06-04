import string
import random

name_length = 8

with open('data.sql','w') as f:
  id = 1
  f.write('insert into customer values ');
  while id<1000000:
    random_str = ''.join([random.choice(string.ascii_letters + string.digits) for i in range(name_length)])
    num_value = random.randint(0,100000)
    f.write('(%d,\'%s\',%d)' % (id,random_str,num_value))
    if id<1000000-1:
      f.write(',')
    else:
      f.write(';')
    id+=1

