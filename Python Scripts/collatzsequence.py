def collatz(number):
    if number%2==0 :
        return number//2
    else:
        return 3*number+1
number=int(input('enter a number '))
collatz_ = []
while True:
    n=collatz(number)
    print(n)
    collatz_.append(n)
    number=n
    if number==1 :
        break
print(str(len(collatz_))+" is the length of the sequence")    
       
