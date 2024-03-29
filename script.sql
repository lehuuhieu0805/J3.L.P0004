USE [master]
GO
/****** Object:  Database [J3.L.P0004]    Script Date: 11/01/2023 23:19:10 ******/
CREATE DATABASE [J3.L.P0004]
GO
USE [J3.L.P0004]
GO
/****** Object:  Table [dbo].[Article]    Script Date: 11/01/2023 23:19:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Article](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](255) NULL,
	[ShortDescription] [nvarchar](255) NULL,
	[Content] [nvarchar](255) NULL,
	[PostingDate] [datetime] NULL,
	[Status] [varchar](255) NULL,
	[UserEmail] [varchar](255) NOT NULL,
 CONSTRAINT [PK__Article__3214EC0793C9F89F] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 11/01/2023 23:19:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Description] [nvarchar](255) NULL,
	[UserEmail] [varchar](255) NOT NULL,
	[ArticleId] [int] NOT NULL,
 CONSTRAINT [PK_Comment] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 11/01/2023 23:19:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[Email] [varchar](255) NOT NULL,
	[Name] [nvarchar](255) NULL,
	[Password] [varchar](255) NULL,
	[Role] [varchar](255) NULL,
	[Status] [varchar](255) NULL,
	[Code] [varchar](6) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD  CONSTRAINT [FK__Article__UserEma__787EE5A0] FOREIGN KEY([UserEmail])
REFERENCES [dbo].[User] ([Email])
GO
ALTER TABLE [dbo].[Article] CHECK CONSTRAINT [FK__Article__UserEma__787EE5A0]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK__Comment__Article__7C4F7684] FOREIGN KEY([ArticleId])
REFERENCES [dbo].[Article] ([Id])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK__Comment__Article__7C4F7684]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK__Comment__UserEma__7B5B524B] FOREIGN KEY([UserEmail])
REFERENCES [dbo].[User] ([Email])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK__Comment__UserEma__7B5B524B]
GO
USE [master]
GO
ALTER DATABASE [J3.L.P0004] SET  READ_WRITE 
GO
