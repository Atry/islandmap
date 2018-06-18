package zhihulive.islandmap

import org.scalatest.{FreeSpec, GivenWhenThen, Inside, Matchers}
import zhihulive.islandmap.IslandMapBuilder.IslandCache

/**
  * @author 杨博 (Yang Bo)
  */
final class IslandMapBuilderSpec extends FreeSpec with Matchers with Inside with GivenWhenThen {

  "test measureIslandSizeAt" in {
    val landGrid = IndexedSeq(
      IndexedSeq(true, true, false),
      IndexedSeq(true, false, true)
    )

    val cache = IslandCache(landGrid)

    val island = IslandMapBuilder.measureIslandSizeAt(landGrid, 0, 1, cache)
    island.size should be(3)

    cache(0)(1) should be(Some(island))
    cache(0)(0) should be(Some(island))
    cache(1)(0) should be(Some(island))
    cache(1)(2) should be(None)

  }

  "Sample input" in {
    val landGrid =
      IndexedSeq(
        IndexedSeq(false, false, false, true, true, false, false, false, false, false),
        IndexedSeq(false, false, false, true, true, false, false, true, false, false),
        IndexedSeq(false, false, false, false, false, true, false, false, false, false),
        IndexedSeq(false, false, false, false, true, true, true, false, false, false),
        IndexedSeq(true, false, false, false, true, true, true, false, true, true),
        IndexedSeq(true, true, false, false, false, true, false, true, false, false),
        IndexedSeq(false, false, false, false, false, false, false, true, false, false),
        IndexedSeq(false, false, false, true, true, false, false, true, false, false),
        IndexedSeq(false, false, false, true, true, true, true, false, false, true),
        IndexedSeq(false, false, true, false, false, false, false, false, false, false)
      )
    val (islands, islandGrid) = IslandMapBuilder.groupByIsland(landGrid)
    inside(islandGrid(0)(3)) {
      case Some(island) =>
        island.size should be(4)
    }
    inside(islandGrid(4)(5)) {
      case Some(island) =>
        island.size should be(8)
    }
    islandGrid(7)(2) should be(None)

    inside(islandGrid(5)(5), islandGrid(3)(6)) {
      case (Some(island0), Some(island1)) =>
        island0 should be(island1)
    }
    inside(islandGrid(2)(8), islandGrid(7)(8)) {
      case (None, None) =>
    }

    inside(islandGrid(9)(2), islandGrid(4)(0)) {
      case (Some(island0), Some(island1)) =>
        island0 shouldNot be(island1)
    }

    islands.size should be(9)

    islands.maxBy(_.size).size should be(8)
  }

}
