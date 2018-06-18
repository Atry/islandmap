package zhihulive.islandmap

/**
  * An utility class to build [[models.IslandGrid]]
  */
object IslandMapBuilder {

  private[islandmap] type IslandCache = IndexedSeq[Array[Option[models.Island]]]

  private[islandmap] def measureIslandSizeAt(landGrid: models.LandGrid,
                                             y: Int,
                                             x: Int,
                                             cache: IslandCache): models.Island = {
    var count = 0
    val island = new models.Island {
      def size: Int = count
    }

    def depthFirstSearch(y: Int, x: Int): Unit = {
      if (landGrid.isDefinedAt(y)) {
        val row = landGrid(y)
        if (row.isDefinedAt(x) && row(x) && cache(y)(x) == None) {
          count += 1
          cache(y)(x) = Some(island)
          depthFirstSearch(y, x + 1)
          depthFirstSearch(y, x - 1)
          depthFirstSearch(y + 1, x)
          depthFirstSearch(y - 1, x)
        }
      }
    }

    depthFirstSearch(y, x)

    island
  }

  private[islandmap] def IslandCache(landGrid: models.LandGrid): IslandCache = {
    landGrid.map { row =>
      Array.fill[Option[models.Island]](row.length)(None)
    }
  }

  /**
    * Groups lands into islands.
    *
    * @return A pair of a set of all islands, and the grid to query island by position.
    */
  def groupByIsland(landGrid: models.LandGrid): (Set[models.Island], models.IslandGrid) = {
    val islandSetBuilder = Set.newBuilder[models.Island]

    val cache = IslandCache(landGrid)

    for ((row, y) <- landGrid.zipWithIndex) {
      for ((isLand, x) <- row.zipWithIndex) {
        if (isLand && cache(y)(x) == None) {
          val island = measureIslandSizeAt(landGrid, y, x, cache)
          islandSetBuilder += island
        }
      }
    }

    (islandSetBuilder.result(), cache.map(_.toIndexedSeq))

  }

}
