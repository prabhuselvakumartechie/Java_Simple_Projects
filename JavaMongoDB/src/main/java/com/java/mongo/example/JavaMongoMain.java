/**
 * 
 */
package com.java.mongo.example;

import com.java.mongo.example.utils.Contants;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author prabhu.selvakumar
 *
 */
public class JavaMongoMain {

	final static Logger logger = Logger.getLogger(JavaMongoMain.class);
	static int idValue = 001;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		JavaMongoMain javaMongoMain = new JavaMongoMain();

		logger.info("Main Program has been started");

		FileReader fReader = null;
		try {
			fReader = new FileReader(Contants.FILE_LOCATION);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties properties = new Properties();
		properties.load(fReader);

		String hostName = properties.getProperty(Contants.HOST);
		int postNo = Integer.valueOf(properties.getProperty(Contants.PORT));
		String databaseName = properties.getProperty(Contants.DATABASE_NAME);
		String dataCollectionName = properties.getProperty(Contants.DB_COLLECTION_NAME);

		MongoClient mongoClient = new MongoClient(hostName, postNo);
		MongoDatabase mongoDB = mongoClient.getDatabase(databaseName);
		MongoCollection<Document> mongoCollection = mongoDB.getCollection(dataCollectionName);
		javaMongoMain.insertValue(mongoCollection);
		
	}

	public boolean insertValue(MongoCollection mongoCollection) {
		Document doc = generateDocument();
		mongoCollection.insertOne(doc);
		return true;
	}
	
	
	/**
	 * Generated document
	 * @return Document
	 */
	public Document generateDocument() {
		Document doc = new Document("Name", "Prabhu");
		doc.append("Address", "Erode");
		doc.append("Gender", "Male");
		doc.append("DOB", "23-05-1991");
		doc.append("ID", idValue);
		++idValue;
		return doc;
	}

}
