Fruit_dict={"Apple":100, "Orange":50, "Grapes":70, "Papaya":40}
Availability = input("What are you looking for? ").lower()
 
if(Availability in Fruit_dict):
    print("Yes, this is available")
else:
    print("No, this is not available")
