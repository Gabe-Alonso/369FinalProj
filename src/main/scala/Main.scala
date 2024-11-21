import org.apache.spark.SparkContext._
import scala.io._
import org.apache.spark.{ SparkConf, SparkContext }
import org.apache.spark.rdd._
import org.apache.log4j.Logger
import org.apache.log4j.Level
import scala.collection._

object Main {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.OFF);
    Logger.getLogger("akka").setLevel(Level.OFF);

    val conf = new SparkConf().setAppName("Lab6").setMaster("local[4]");
    val sc = new SparkContext(conf);

    val data = sc.textFile("recruitment_data.csv");

    // Looking at Age on Interview Score
    val data_points = data.map(line => line.split(","))
      .map(tokens => (tokens(0).toInt, tokens(6).toInt))

    val n = data_points.count();
    val x_sum = data_points.map(_._1).sum
    val y_sum = data_points.map(_._2).sum
    val xysum = data_points.map({case (x, y) => x * y}).sum
    val x_square_sum = data_points.map({case (x, y) => x * x}).sum

    val slope = ((n * xysum) - (x_sum * y_sum)) / ((n * x_square_sum) - x_sum * x_sum)
    val test_point = (x_sum / n, y_sum / n)
    val y_int = test_point._2 - (slope * test_point._1)
    println("y = " + slope + "x + " + y_int)
  }
}