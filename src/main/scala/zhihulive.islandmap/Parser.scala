package zhihulive.islandmap

/**
 * A parser that converts the grid texts to a [[models.LandGrid]]
 */
object Parser {

  final val SeaPixel = '~'
  final val LandPixel = 'x'

  /**
   * Returns a [[models.LandGrid]] from `source`.
   *
   * @param source A multi-line string that contains `~` and `x` characters.
   * @throws IllegalArgumentException if the input `source` is not valid.
   */

  def parse(source: String): models.LandGrid = {
    val landMap = source.lines.map { row =>
      row.map {
        case SeaPixel =>
          false
        case LandPixel =>
          true
        case unexpectedCharacter =>
          throw new IllegalArgumentException(s"Unexpected character: $unexpectedCharacter")

      }
    }.toIndexedSeq
    landMap.headOption match {
      case None =>
      case Some(firstRow) =>
        val width = firstRow.length
        if (landMap.exists(_.length != width)) {
          throw new IllegalArgumentException("Each row in the map must have the same length")
        }
    }
    landMap
  }

}
