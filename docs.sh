#!/bin/bash

# Start the right place
# ------------------------------------------------------------
cd /Users/cl/Dropbox/DNB/API/dnb

# Github
# ------------------------------------------------------------
git add 7580.json
git commit -m "(Automatic push from documentation build)"
git push

# Spectacle
# ------------------------------------------------------------
spectacle 7580.json -t 7580
scp -r 7580/* superelectric@login.domeneshop.no:www/tmp/7580/

# Slate
# ------------------------------------------------------------
cd ../slate
widdershins ../dnb/7580.json -o source/index.html.md 
bundle exec middleman build --clean
scp -r scp -r build/* superelectric@login.domeneshop.no:www/tmp/7580.slate/

