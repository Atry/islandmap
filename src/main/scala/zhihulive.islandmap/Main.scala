package zhihulive.islandmap

import java.nio.file.{Files, Paths}

import scala.io.Codec

/**
  * The entry point of the application.
  *
  * Usage:
  * ```
  * scala islandmap.jar input.txt
  * ```
  * or
  * ```
  * sbt "run input.txt"
  * ```
  *
  */
object Main {

  /**
   * The main method of the application.
   * @param arguments The command line arguments.
   */
  def main(arguments: Array[String]): Unit = {
    val inputPath = arguments match {
      case Array(inputFileName) => {
        Paths.get(inputFileName)
      }
      case _ => {
        sys.error("Usage:\nscala islandmap.jar input.txt")
      }
    }

    val landGrid = Parser.parse(new String(Files.readAllBytes(inputPath), Codec.UTF8.charSet))
    val (islands, islandGrid) = IslandMapBuilder.groupByIsland(landGrid)

    println(raw"""Output #1: ${islandGrid(0)(3) match {
      case Some(island) => island.size
      case None         => "NO ISLAND"
    }}""")

    println(raw"""Output #2: ${islandGrid(4)(5) match {
      case Some(island) => island.size
      case None         => "NO ISLAND"
    }}""")

    println(raw"""Output #3: ${islandGrid(7)(2) match {
      case Some(island) => island.size
      case None         => "NO ISLAND"
    }}""")

    println(raw"""Output #4: ${(islandGrid(5)(5), islandGrid(3)(6)) match {
      case (Some(island0), Some(island1)) =>
        if (island0 == island1) {
          "SAME"
        } else {
          "DIFFERENT"
        }
      case _ => "NO ISLAND"
    }}""")

    println(raw"""Output #5: ${(islandGrid(2)(8), islandGrid(7)(8)) match {
      case (Some(island0), Some(island1)) =>
        if (island0 == island1) {
          "SAME"
        } else {
          "DIFFERENT"
        }
      case _ => "NO ISLAND"
    }}""")

    println(raw"""Output #6: ${(islandGrid(9)(2), islandGrid(4)(0)) match {
      case (Some(island0), Some(island1)) =>
        if (island0 == island1) {
          "SAME"
        } else {
          "DIFFERENT"
        }
      case _ => "NO ISLAND"
    }}""")

    println(raw"""Output #7: ${islands.size}""")

    println(raw"""Output #8: ${islands.maxBy(_.size).size}""")

  }
}
