# So one of my friends got an UNSUCCESSFUL result.
# But this is MU so there can be a mistake.
# This program creates a bot which checks
# If there are any changes.

# This is version 2... I had a version 1 which could download all UNSUCCESSFUL results.
# But I scraped that because I don't know the range of my college tickets.
# I made that because one of my friends forgot their hall ticket ID.

# Kinda dumb but it's pretty cool.

import requests
from time import sleep
from datetime import datetime
from bs4 import BeautifulSoup

url = r"http://results.mu.ac.in/get_resultc.php"

while 1:
    params = {
            "exam_id": 4529,
            "exam_year": 2017,
            "exam_month": "DEC",
            "seat_no": 13107175,
            "submit": "Go"
            }

    r = requests.post (url, params)
    soup = BeautifulSoup (r.text, 'html.parser')
    if not "SUCCESSFUL." in soup.b:
        print (f"{str (datetime.now ()) } : No change")
        sleep (5)
        continue
    else:
        print (f"{str (datetime.now ()) } : CHANGEDDDD")
        break
