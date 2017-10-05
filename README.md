# OptimumInventoryPriceCalculator
Tells you the optimum price for delivering your inventory from InventoryStores location at different places

### Inventory-OptCost
=================

###### Inventory Management: Problem Statement
Recently a new online store opened on the Internet that sells iPods & iPhones. They are
faced with an interesting problem of managing their inventory distributed across 2
countries: Brazil and Argentina. You have been assigned to write a program to minimize
the sale price for online customers based on its inventory in these 2 countries.
The price are different based on the country where the inventory is stored. The inventory
in the two countries is limited. There are 100 iPods in Brazil which have a sale price of $65
whereas there are 100 in Argentina which have a sale price of $100. There are 100 iPhone
in Brazil which have a sale price of $100 whereas there are 50 in Argentina which have a
sale price of $150. For simplicity we will use US dollars as our transaction currency.
The order should be fulfilled fully or not at all. If the inventory from one country in used up
item have to be fetched from the other country. There is a transport cost involved when
the item needs to be shipped from one country to another in case the purchase country is
different than the inventory country. Shipping cost is $400 for every 10 units of item type
(no mixing of item types). Note that transport cost is always in multiples of 10 units. If
customer passport belongs to local country then the customer will get 20% discount on
the transport cost. For example a customer placing order from Argentina and has a
passport of Brazil, while shipping order from Brazil the transport cost will be charged
($400 - 20% = $320) for every 10 units. To identify passport from brazil, passport number
starts with B, followed by 3 digits, followed 2 chars, followed by 7 alphanumeric characters.
And for Argentina, passport number starts with A, followed by 2 chars, followed by 9
alphanumeric characters.
 To minimize the total sales price it can be partly fulfilled from one country and remaining
from other. Assume that before each purchase order the inventory is replenished to its
normal level. Thus the orders are independent of each other.

Following is the input/output to understand the problem better.

* INPUT FORMAT: (no space between separators)
<purchase_country>:<optional_passport_number>:<item_type>:<number_of_
units_to_be_ordered>:<item_type>:<number_of_units_to_be_ordered>

* OUTPUT FORMAT:
<total_sale_price>:IPHONE:<iphone_brazil_inventory>:<iphone_argentina_inventory>:
IPOD:<ipod_brazil_inventory>:<ipod_argentina_inventory>

INPUT 1: </br>
BRAZIL:B123AB1234567:IPHONE:20:IPOD:10 </br>
OUTPUT 1: </br>
2650:IPHONE:80:50:IPOD:90:100 </br>

INPUT 2: </br>
ARGENTINA:B123AB1234567:IPHONE:22:IPOD:10 </br>
OUTPUT 2: </br>
3910:IPHONE:80:48:IPOD:90:100 </br>

INPUT 3: </br>
BRAZIL:AAB123456789:IPHONE:125:IPOD:70 </br>
OUTPUT 3: </br>
19510:IPHONE:5:20:IPOD:30:100 </br>

INPUT 4: </br>
ARGENTINA:AAB123456789:IPOD:50:IPHONE:25 </br>
OUTPUT 4: </br>
8550:IPHONE:80:45:IPOD:100:50 </br>

INPUT 5: </br>
BRAZIL:IPHONE:50:IPOD:150 </br>
OUTPUT 5: </br>
18500:IPHONE:50:50:IPOD:0:50 </br>

INPUT 6: </br>
BRAZIL:IPHONE:250:IPOD:150 </br>
OUTPUT 6: </br>
** *********OUT OF STOCK********** ** </br>
 Sorry!!! Stores can't serve your request
