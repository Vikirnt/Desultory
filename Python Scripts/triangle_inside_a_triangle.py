def odd(n):#defining the function for finding the odd number
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
            m = int((n-1)/2)#setting the middle value
            l = int(2*(n-2-m)-1)#setting the middle space width for (middle+1)th line
            step = 0
            for i in range(n):
                for j in range(n-1-i):#to print spaces in the upper left triangle
                    print(" ",end='')
                if i == (n-1):#for the last line which has to be all in star
                    print("* "*n)
                elif i == 0:#for first star ,the peak
                    print("*")
                elif i == m :#for middle triangle
                    print("* "*(m+1))
                elif i>m :#for the inner triangle
                    print("*"+" "*(2*(i-m)-1)+"*"+" "*(l-2*step)+"*"+" "*(2*(i-m)-1)+"*")# in each line after the middle one the space increases in series of odd number that is why that formula has been used.
                    step +=1

                else:
                    print("*"+" "*(i*2-1)+"*")
        else:
            print("I asked to enter an odd integer you dumb motherfucker")
