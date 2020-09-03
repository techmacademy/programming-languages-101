# A Function that returns the smallest of the two arguments
def returnSmallest(a, b):
	if (a > b):
		return b
	else:
		return a

# Add your functions below this line :) --------------------------------------

# a function to print a cat saying something on a 'balloon'
def cat_say(text):
    """Generate a picture of a cat saying something"""
    text_length = len(text)
    print('            {}'.format('_' * text_length))
    print('          < {} >'.format(text))
    print('            {}'.format('-' * text_length))
    print('          /')
    print(' /\_/\   /')
    print('( o.o )')
    print(' > ^ <')


# A function to print a reversed message
def reverseMessage(x):
	b=[]
	r=""
	b=x.split()
	for i in range((len(b)-1),-1,-1):
		r=r+b[i]+""
	return print(r)
