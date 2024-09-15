Yes, the SEC does provide standardized templates and guidelines for companies to follow when submitting their financial reports. These templates are part of a system called XBRL (eXtensible Business Reporting Language), which was implemented to make financial data more consistent, comparable, and machine-readable.
Here's an overview of how the SEC standardizes financial reporting:

XBRL (eXtensible Business Reporting Language):

The SEC requires companies to submit their financial statements in XBRL format.
XBRL uses a standardized taxonomy (a set of tags) to label financial data elements.


US GAAP Taxonomy:

This is a comprehensive set of financial reporting concepts that companies use to tag their financial statements.
It's updated annually to reflect changes in accounting standards and reporting practices.


SEC Forms:

While not templates in the traditional sense, the SEC provides specific forms (like 10-K and 10-Q) that dictate the structure and content of financial reports.


Regulation S-X:

This SEC regulation prescribes the form and content of financial statements to be filed with the SEC.


EDGAR Filer Manual:

This manual provides detailed technical specifications for submitting electronic filings to the SEC.



While these standards ensure consistency, companies still have some flexibility in how they present their financial information within these guidelines. This is why you'll still see variations in how different companies report their financials.
For your project of creating a standardized dataset, you have a few options:

Use XBRL Data:

The SEC provides XBRL data for download, which can be parsed programmatically.
URL: https://www.sec.gov/dera/data/financial-statement-data-sets


Interactive Data Viewer:

The SEC provides an online tool to view and compare XBRL data across companies.
URL: https://www.sec.gov/edgar/searchedgar/companysearch.html (Click on "Interactive Data" for a specific filing)


US GAAP Taxonomy:

You can review the standardized tags to understand how financial concepts are categorized.
URL: https://xbrl.us/xbrl-taxonomy/2023-us-gaap/


Sample XBRL filings:

The SEC provides sample XBRL filings to demonstrate correct formatting.
URL: https://www.sec.gov/info/edgar/edgartaxonomies.shtml



If you're interested in creating a truly standardized dataset, you might consider:

Focusing on the core financial statements (Income Statement, Balance Sheet, Cash Flow Statement) which are the most standardized across companies.
Using XBRL data, which is already tagged and categorized according to the US GAAP Taxonomy.
Developing a program to extract specific XBRL tags across multiple company filings, ensuring you're comparing the same data points.
Being aware of industry-specific variations in reporting, even within the standardized framework.

Remember, while the SEC provides these standards and templates, interpreting financial data still requires careful analysis and understanding of each company's specific context and industry. The standardization helps with comparability, but it doesn't eliminate the need for thoughtful analysis.