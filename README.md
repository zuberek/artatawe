# software-assignment-

Cs-230 Assignment 3, deadline 11th of December


# Database

You will need to add the SQLLite JAR to the project for my database function to work. 

To do that, right click your project > properties > java build path > add external jar and add the SQLlite jar.
You can get the latest jar from: https://bitbucket.org/xerial/sqlite-jdbc/downloads/

# JavaFX

You must make sure that your IDE supports JavaFX 2 for the GUI classes to work. For Eclipse it involves downloading "e(fx)clipse" plug in: https://marketplace.eclipse.org/content/efxclipse, for IntelliJ it's included.

# ToDo

###Josh
- Validate Address using new Class
- Dashboard items need to work
- Maybe multiple photo upload
- Testing
- Video demo's

# Software Requirements
## Account with account information 
	- Username
	- First/Last name
	- VALID UK mobile phone number
	- VALID UK address WITH POSTCODE
	- last login information  (date/time)
	- profile picture
	
## Two pieces of Artwork, each **must** have
	- Title
	- Description (optional)
	- main photo
	- name of creator
	- year it was created
	- Reserve Price
	- Number of bids allowed
	- date/time was placed onto auction
		- Painting Spec
			- Dimensions (width/height)
		- Sculpture Spec
			- Dimensions (width/height/depth)
			- Main material
			- Additional photos (optional)

## Users should be able to browse through all artwords
	- Look through images with title and description on display
	- Be able to see more detailed information if clicked
	- Should be able mark the seller as favourite
	
## Bidding rules
	- If the user is the highest bidder they cant bid and informed why
	- If the bid is lower than reservice price then it is refused and user is informed why
	- If bid is lower OR equal to current bid it refused and user is informed why
	- The bid needs to be higher than reserve price and current highest bid to be accepted
	- Bidding continues until the maximum amount of bids is reached.
	- The winner must be informed they won the auction

##  Favourite Users
	- Users should be able to view LIMITED profiles of other users (username and proifle image) and mark them as favourites

## Bid Histories
	- See list of bids in chronological order that they have placed (also shows the artwork it was placed on) and show date/time/amount of that bid
	- Sellers should be able to see a list of bids in chronological order that have been placed on there auction, with user/date/time/amount 
		
## Won artworks
	- Users should be able to see artworks for which they placed winning bid
	- Be able to see list of artworks they have succesfully auctioned and dispay name of artwork, winning bidder and the winning bid.
