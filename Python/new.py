# A simple program that uses loops, list objects, function , and conditional statements

# Pass in key word arguments into the function definition my_name
def my_name(first_name='Albert', last_name='Einstein', age=46):
    return 'Hello World, my name is: {} {}, and I am {} years old'.format(first_name,last_name, age)
print(my_name('Marcus', 'Garvey', 96))

# Create list objects and concantenate two list object variables
my_list = [1,2,3,4,5, "python", 43.98, "fish", True]
my_list2 = ['grapes', 'oranges','Aegis', 100, {'Hotel':'Marriott'}, {'City':'San Francisco'}]
new_list = my_list + my_list2


# iterate through the new_list variable and reverse its content
for letter in new_list:
    print(new_list[-2]) # Print the ninth index in new_list
    print(new_list[::-1]) # reverse new_list 
#check if the correct interger and string text is in the list
if 5 in new_list and "python" in new_list:
    print("That is correct, python is the best programming langauge in the world!")
else:
      print("Please chose the correct string or interger value!")




