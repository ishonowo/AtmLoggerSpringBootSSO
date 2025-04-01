<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ATM Issue Notification</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            color: #333333;
            max-width: 600px;
            margin: 0 auto;
        }
        .header {
            background-color: #003366;
            color: white;
            padding: 15px;
            text-align: center;
        }
        .content {
            padding: 20px;
            background-color: #f9f9f9;
        }
        .issue-details {
            background-color: white;
            border: 1px solid #dddddd;
            padding: 15px;
            margin: 20px 0;
            border-radius: 5px;
        }
        .detail-row {
            display: flex;
            margin-bottom: 10px;
            border-bottom: 1px solid #eeeeee;
            padding-bottom: 5px;
        }
        .label {
            font-weight: bold;
            width: 40%;
        }
        .value {
            width: 60%;
        }
        .footer {
            margin-top: 20px;
            font-size: 0.9em;
            text-align: center;
            color: #666666;
        }
        .urgent {
            color: #cc0000;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="header">
        <h2>ATM Issue Notification</h2>
    </div>
    
    <div class="content">
        <p>${intro}</p>
        
        <div class="issue-details">
            <div class="detail-row">
                <div class="label">ATM LOCATION:</div>
                <div class="value">${physicalAddress}</div>
            </div>
            
            <div class="detail-row">
                <div class="label">BRANCH IN CHARGE:</div>
                <div class="value">${branchName}</div>
            </div>
            
            <div class="detail-row">
                <div class="label">VENDOR:</div>
                <div class="value">${vendorName}</div>
            </div>
            
            <div class="detail-row">
                <div class="label">ISSUE(S):</div>
                <div class="value"><#if issueDesc?contains("urgent") || issueDesc?contains("Urgent")><span class="urgent">${issueDesc}</span><#else>${issueDesc}</#if></div>
            </div>
            
            <div class="detail-row">
                <div class="label">BRANCH CONTACT:</div>
                <div class="value">${branchLogger}</div>
            </div>
            
            <div class="detail-row">
                <div class="label">CONTACT PHONE NUMBER:</div>
                <div class="value">${loggerPhone}</div>
            </div>
            
            <div class="detail-row">
			    <div class="label">DATE LOGGED:</div>
			    <div class="value">${dateLogged?string('yyyy-MM-dd HH:mm:ss')}</div>
			</div>
        </div>
        
        <p>${conclusion}</p>
    </div>
    
    <div class="footer">
        <p>This is an automated notification. Please do not reply directly to this email.</p>
    </div>
</body>
</html>