package org.ashw.hortonworks_word_count;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class App {
	public static void main(String[] args) {
		// Create a SparkContext to initialize
		List<CollectorsObject> listObj = new ArrayList<CollectorsObject>();
		SparkConf conf = new SparkConf().setMaster("local").setAppName("Word Count");

		// Create a Java version of the Spark Context
		@SuppressWarnings("resource")
		JavaSparkContext sc = new JavaSparkContext(conf);

		// Load the text into a Spark RDD, which is a distributed representation of each
		// line of text
		JavaRDD<String> textFile = sc.textFile("src/shakespeare.txt");
		JavaPairRDD<String, Integer> counts = textFile.flatMap(s -> Arrays.asList(s.split("[ ,]")).iterator())
				.mapToPair(word -> new Tuple2<>(word, 1)).reduceByKey((a, b) -> a + b);
		Iterator<Tuple2<String, Integer>> itr = counts.toLocalIterator();

		while (itr.hasNext()) {
			listObj.add(new CollectorsObject(itr.next()._1(), itr.next()._2()));
		}

		Collections.sort(listObj);

		System.out.println(listObj + " Size : " + listObj.size());
		// counts.saveAsTextFile("src/shakespeareWordCount");
	}
}
