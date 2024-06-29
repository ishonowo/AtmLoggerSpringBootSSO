<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    

    <style>
  
        table, th, td {border: 1px solid black; border-collapse: collapse;}
    </style>
</head>

<body>
	                <p>${emailIssue.mIntro}</p>
	                <table>
	                        
	                           <tr> 
	                          	<#list emailIssue.mHeader as m>
	                             <th>${m}</th>
	                            </#list>
	                           </tr>
	                        
	                        
	                           <tr> 
	                              <#list emailIssue.mBody as mb>
	                              <td>${mb}</td>
	                              </#list> 
	                           </tr>
	                        
	                </table>
	                <br/>
	                <p>${emailIssue.mEnd}</p>
</body>	


</html>