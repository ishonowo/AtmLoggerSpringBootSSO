IF OBJECT_ID('dbo.[vendor_terminal]', 'U') IS NOT NULL
DROP TABLE dbo.[vendor_terminal];

CREATE TABLE dbo.[vendor_terminal](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[vendor_name] [varchar](40) NOT NULL,
	[terminal_id] [varchar](8) NOT NULL,
	[atm_name] [varchar](50) NOT NULL,
	[offsite] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

IF OBJECT_ID('dbo.[branch_info]', 'U') IS NOT NULL
DROP TABLE dbo.[branch_info];

CREATE TABLE dbo.[branch_info](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[sol_id] [varchar](3) NOT NULL,
	[branch_email] [varchar](100) NOT NULL,
	[physical_address] [varchar](200) NOT NULL,
	[branch_name] [varchar](100) NOT NULL,
	[region] [varchar](100) NOT NULL,	
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

IF OBJECT_ID('dbo.[vendor_contact]', 'U') IS NOT NULL
DROP TABLE dbo.[vendor_contact];

CREATE TABLE dbo.[vendor_contact](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[vendor_name] [varchar](40) NOT NULL,
	[contact] [varchar](500) NOT NULL,
	[status] [bit] NOT NULL,	
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];