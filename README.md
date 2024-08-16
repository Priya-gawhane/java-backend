This is the Java based Backend application to perform various activities on pdf, images and audio. The Backend application uses Java Springboot 3.3.2 version with gradle build to facilitate the services to the UI. Some of the common uses of this servies include such as 
1. Merging the PDF, Image, Audio.
2. Compressing the PDF, Image and Audio.
3. Convert the PDF, Image and Audio.

We here use REST = Representation State Transfer http method to communicate with the server and facilitate out needs. More over each and every action has its own API - Application Programming Interface which works on its own implementations and requirements. This particular Project does not use or get anu help of any kind of databases or cloud facilities to store the document or datas, instead all the created or uploaded documents are stored in system memory and are highly subjected to volite since even the backend logic does not store the session progress or the order made by the user..
