"""
    My friend Shloak Patil wanted a web scrapper for www.espncricinfo.com for his internship. This is the program that I wrote to scrap the HTML tables from the website into CSV files.

    date: 29/05/2018
    requirements:
        requests
        beautifulsoup4
"""

from bs4 import BeautifulSoup as bs
from functools import reduce
import requests
import csv
import re

meta_headers = ['Team1', 'Team2', 'Winner', 'Ground', 'Date', 'Match_no', 'link']
card_headers = ['TeamName', 'inns', 'fours', 'sixes', 'xtras_receive', 'total', 'wkts_lost']

class MatchMeta:
    """ MatchMeta object has 2 properties.
        1. data: Meta data about the match.
        2. innings: Data about each inning.
        Each match occupies 3 rows.
    """
    def __init__ (self, body):
        self.data = {} # Empty dictionary.
        self.innings = []
        for i in range (7):
            self.data [meta_headers [i]] = body [i]

    def print (self):
        for i in self.get_innings ():
            print (str (i))

    def get_innings (self):
        for i in self.innings:
            row = [] # Empty row
            row.append (self.data [x] for x in meta_headers)
            row.append (i [x] for x in card_headers)
            yield row
        

def scrap_cards (match):
    """ Get data of the 4 innings. """

    scorecards = bs (requests.get (match.data ['link']).text, 'html.parser').find_all ('article', {'class': 'scorecard'})
    for card in scorecards:
        inn = {} # Empty dictionary.

        # Who's Inning? Team and number
        inn ['TeamName'], inn ['inns'] = re.findall (r'(\w+\s?\w+?) (\d)\w+ .*', card.h2.text) [0]

        # Scrap batsmen
        batsmen = card.find_all ('div', {'class': 'wrap batsmen'})
        sixes = []
        fours = []
        for b in batsmen:
            r = b.find_all ('div', {'class': 'cell runs'})
            fours += [r [3].text]
            sixes += [r [4].text]
        # Find sums of fours and sixes
        inn ['fours'] = reduce (lambda x, y: x + y, map (lambda x: int (x), fours))
        inn ['sixes'] = reduce (lambda x, y: x + y, map (lambda x: int (x), sixes))
        
        # Find extras
        inn ['xtras_receive'] = int (re.findall (r'(\d+)', card.find ('div', {'class': 'wrap extras'}).find_all ('div') [1].text) [0])

        # Find total
        totaldiv = card.find ('div', {'class': 'wrap total'}).find_all ('div') [1].text
        inn ['total'] = re.findall (r'(\d+)', totaldiv) [0]
        # Find wickets lost
        if len (re.findall (r'all out', totaldiv)):
            inn ['wkts_lost'] = 10
        else:
            inn ['wkts_lost'] = int (re.findall (r'(\d+)', totaldiv) [1])
        
        # Find overs bowled
        overs = re.findall (r'(\d*.?\d*?) Overs', totaldiv) [0]

        match.innings += [inn]
    # Return the match again
    return match


def scrap_table (link):
    year = []

    """ Convert table HTML to CSV. """
    table = bs (requests.get (link).text, 'html.parser').find ('table', {'class': 'engineTable'})

    # Extract data
    for row in table.tbody.find_all ('tr'):
        # Get all the data from the cells
        body = [data.text for data in row.find_all ('td')]
        
        # Format the data
        body.pop (3) # Removes margin.
        body [5] = body [5].split () [2] # Get match ID
        
        # Get the link from table row.
        body.append (r'http://www.espncricinfo.com' + row.find_all ('td') [-1].a ['href'])

        # Make a match object.
        match = scrap_cards (MatchMeta (body))

        match.print ()
        year += [x for x in match.get_innings ()]
    return year


# Main loop.
target = lambda year: f"http://stats.espncricinfo.com/ci/engine/records/team/match_results.html?class=1;id={year};type=year"

with open ('x.csv', 'w') as f:
        writer = csv.writer (f)
        writer.writerow (meta_headers + card_headers)
        
        for year in range (2005, 2006, 1): # 2005 - 2018
            rows = scrap_table (target (year))
            for row in rows:
                writer.writerow (row)

