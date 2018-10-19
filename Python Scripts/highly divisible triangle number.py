"""
This program prints the smallest number which have divisors more then 500.
Basic logic is to give an input of an integer type number to a function named divisors which
makes an array of divisors and baased on the value of the square root of the input number number of divisors is returned to the main loop.
"""
from math import sqrt
n=0

def divisors(n):
    m=int(sqrt(n))
    l=float(sqrt(n))
    div=[]
    for i in range(1,m+1):
        if n%i == 0 :
            div.append(i)
    if l-m == 0:
        return (2*len(div)-1)
    else:
        return (2*len(div))
            
    
for i in range(1,100000000000000000000):
    n+=i
    num_of_divisors = divisors(n)
    if num_of_divisors>=500:
        print("The " +str(i)+"th triangle number "+str(n)+" has "+str(num_of_divisors)+"divisors")
        break
    
        
        
    
