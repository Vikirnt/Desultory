"""
    My friend Shloak Patil wanted a web scrapper for www.espncricinfo.com for his internship. This is the program that I wrote to scrap the HTML tables from the website into CSV files.

    date: 29/05/2018
    requirements:
        requests
        beautifulsoup4
"""
from bs4 import BeautifulSoup as bs
import requests
import csv

def get_record_links (url):
    """ Get all record links. """
    # Get the webpage and division.
    index = requests.get (url)
    focus = bs (index.text, 'html.parser').find ('div', {'class': 'pnl386M'})
    # Remove the same-page navigation links.
    focus.find ('div', {'class': 'RecTstCrvM'}).decompose ()
    # Return links.
    return ['http://stats.espncricinfo.com' + link ['href'] for link in focus.find_all ('a', {'class': 'RecordLinks'}) ]

def scrap_table (link):
    """ Convert table HTML to CSV. """
    # Get one table from the page. This assumes that the page has only one table.
    page = requests.get (link)
    table = bs (page.text, 'html.parser').find ('table', {'class': 'engineTable'})

    with open (f'{link.split ("/") [-1].replace ("html", "csv")}', 'w') as f:
        writer = csv.writer (f)
        
        # Extract headers.
        headers = [header.text for header in table.thead.find_all ('th')]
        writer.writerow (headers)

        # Extract rows.
        for row in table.tbody.find_all ('tr'):
            data = [data.text for data in row.find_all ('td')]
            writer.writerow (data)


# Main loop
target = u'http://stats.espncricinfo.com/ci/engine/records/index.html?class=1'

for link in get_record_links (target):
    try:
        scrap_table (link)
    except AttributeError:
        print (f"ERROR: {link}")

