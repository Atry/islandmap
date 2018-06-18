package zhihulive.islandmap

/**
  * A mimic package that contains type definitions for models.
  */
object models {

  /** A row-major 2D grid of an archipelago.
    *
    * @note For each position in the grid, the value `true` stands for land, `false` for sea.
    */
  type LandGrid = IndexedSeq[IndexedSeq[Boolean]]

  /** A row-major 2D grid of the island information.
    *
    * @note For each position in the grid, the value `None` stands for sea, `Some` for the land on a specific island.
    *
    * @see [[IslandMapBuilder.groupByIsland]] to convert an [[IslandGrid]] to a [[LandGrid]].
    */
  type IslandGrid = IndexedSeq[IndexedSeq[Option[Island]]]

  /** The information of an island.
    *
    * @note [[Island]] is not a `case class`, thus two islands do not equal to each other
    *       even when they have the same [[size]].
    */
  trait Island {
    def size: Int
  }
}
