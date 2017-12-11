"""
    This is a Python script which searches through a directory,
    searches for a regex and renames the file to that regex.
    Feel free to use and modify this script.

    Enter your target directory.
    Enter your target regex.
"""

import re
import os
from pathlib import Path

# Get input parameters.
target  = input ("Dir -> ")
pattern = re.compile (input ("Reg -> "))
replacement = input ("Rep -> ")

# Loops through directory.
for filename in os.listdir (target):
    # Search regex.
    match = pattern.search (filename)
    if match != None:
        name = match.group ()
        ext = Path (filename).suffix
        print ("{:<25} -> {:>20}{}".format (filename, name.upper (), ext))
        os.rename (target + "/" + filename, target + "/" + eval (rep) + ext)
        # Note the upper (). You can remove that.
