A text file can be given as input to SentiStrength-SE to detect sentiments of texts. Similar to SentiStrength, SentiStrength-SE also detects sentiments per line of text. The columns per line must be separated by tab if there are multiple columns. If you want to identify sentiments of a particular column then that has to be mentioned in SentiStrength-SE. 

Input Options:

A sample input file named ˜TestInput.txt also given in this directory for reference. Each line in the input file has two columns, which are separated by tab. The first column contains the ID of texts and the second column contains the texts. 

If we want to detect sentimental polarities of texts in second column (i.e., n=2), then we need to go to option 'Input' and click 'Classify column n in file'. An input dialog wil be shown, where we can provide the column number 2 (i.e., n=2). As the final output this option will add sentiment polarities values at the end of the each line.

If we want to save only texts' IDs in fisrt column (i.e., m=1) along with sentimental polarities values of corresposding texts in second column (i.e., n=2), then we can go to option 'Input' and click 'Classify column n in file and save column n and column m to new file'. Again, an input dialog will be shown, where we can provide the columns' numbers as 2,1 where n=2 and m=1.  

This tool can be used for any academic purpose. Any other types of uses are strictly prohibited.