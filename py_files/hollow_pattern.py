def odd(n):
    if n%2 == 0 :
        return False
    else:
        return True
while True:
    print("If you want to exit Press: Q button OR to continue type C")
    entered_key = str(input())
    if entered_key.upper() == 'Q' :
        break
    
    
    else:
        print("enter the number of lines to be printed(odd integer only!!)")
        n = int(input())
        if odd(n):
            for i in range(n):
                for j in range(n-1-i):
                    print(" ",end='')
                if i == (n-1):
                    print("* "*n)
                elif i == 0:
                    print("*")

                else:
                    print("*"+" "*(i*2-1)+"*")
        else:
            print("I asked to enter an odd integer you dumb motherfucker")
