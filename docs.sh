#!/bin/bash

COMMITMESSAGE=$*
MAINDIR=/Users/cl/Dropbox/DNB/API/dnb

echo "$COMMITMESSAGE"
echo "$MAINDIR"

# Start the right place
# ------------------------------------------------------------
cd $MAINDIR

# Github
# ------------------------------------------------------------
git add 7580.json
git add API.json
git commit -m "$COMMITMESSAGE"
git push

# 7580
# ============================================================

# Spectacle
spectacle 7580.json -t 7580
scp -C -r 7580/* superelectric@login.domeneshop.no:www/tmp/7580/

# Slate
cd ../slate
widdershins ../dnb/7580.json -o source/index.html.md 
bundle exec middleman build --clean
scp -C -r build/* superelectric@login.domeneshop.no:www/tmp/7580.slate


# API drafts
# ============================================================
cd $MAINDIR

# Spectacle
spectacle API.json -t dnb
scp -C -r dnb/* superelectric@login.domeneshop.no:www/tmp/dnb/

# Slate
cd ../slate
widdershins ../dnb/API.json -o source/index.html.md 
bundle exec middleman build --clean
scp -C -r build/* superelectric@login.domeneshop.no:www/tmp/dnb.slate
