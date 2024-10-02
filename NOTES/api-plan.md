https://twelvedata.com/

We want to create next.js application which will have 
1- user login,register,forgot-pass,reset pass pages
2- We will have a index page which will have 
    2.1 header
    2.2 intro, companies uses our products,platform,developer-support,why our product, think globally, explore data,ready start sections
    2.4 footer
3- other pages
Products,  Stocks, Forex (FX), Crypto, ETFs, Indices, Exchanges, Search symbols, Excel, Google Sheets
Pricing,Developers,
API documentation,
WebSocket documentation,
Request builder,
Company,
About,
Customers,
Education,
Expertise,
Careers,
Brand assets,
Blog,
News,
Resources,
Support,
Contact,Privacy policy,Terms of use, Sitemap 
3- after user login successfully user will be redirected account page
here we will have a dashboard
4-  Main page: General info abour user status
5-  API Keys page: user will be able to create API Keys regarding his product, regenerate could be a option 
6- Statistic page: about APIs usage
7- Market data page: How to use API
    7.1 API usage
    7.2 Websocket usage
8- Manage Plan: 
    8.1 Monthly-billing: Custom plan,Enterprise, Pro, Growing
    8.2 Annual-billing : Custom plan,Enterprise, Pro, Growing difference two months free usage
9- Billing : my bills
10-Settings: user settings,billing address, change pass tabs

as you understood we will give one api with client API keys regarding user plan
and user will be able to use our api with his key; 
case: 
1- user will authenticate with API Key(secret)
2- We will send him a token which will be available for 1 hour after 1 hour he can refresh token
3- we can create calculate API as an example and api will have multiply,aggregate,divide, substract operations

https://www.nasdaq.com/market-activity/stocks/screener  for db schema
https://www.nasdaq.com/themes/nsdq/onetrust/consent/578a06dd-f15c-46aa-8b1d-7dfe08fd6747/578a06dd-f15c-46aa-8b1d-7dfe08fd6747.json

https://api.nasdaq.com/api/quote/indices?symbol=indu&symbol=ndx&symbol=comp&symbol=rut&symbol=spx
https://api.nasdaq.com/api/screener/stocks?tableonly=false&limit=25
https://www.nasdaq.com/themes/nsdq/onetrust/consent/578a06dd-f15c-46aa-8b1d-7dfe08fd6747/018f595a-44e3-735f-b913-fcc206e9b6ea/en.json
https://cdn.cookielaw.org/vendorlist/iab2V2Data.json
https://api.nasdaq.com/api/quote/watchlist?symbol=aapl%7cstocks&type=Rv