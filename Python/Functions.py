# A Function that returns the smallest of the two arguments
def returnSmallest(a, b):
	if (a > b):
		return b
	else:
		return a

# Add your functions below this line :) --------------------------------------

# A function to print a reversed message
def reverseMessage(x):
	b=[]
	r=""
	b=x.split()
	for i in range((len(b)-1),-1,-1):
		r=r+b[i]+""
	return print(r)

# A function that calculates the factorial of a number recursively 
def factorial(n):
	if (n == 0):
		return 1
	else:
		rest = factorial(n-1)
		return rest * n