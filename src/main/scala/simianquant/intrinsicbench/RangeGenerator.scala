package simianquant.intrinsicbench

object RangeGenerator {
  def apply(from: Double, to: Double, cnt: Int): Array[Double] = {
    val incr = (to - from) / cnt
    val pincr = (to - from) / 5

    var xp0 = from
    var xp1 = from + pincr
    var xp2 = from + pincr * 2
    var xp3 = from + pincr * 3
    var xp4 = to - incr

    val res = new Array[Double](cnt)
    var i = 0
    while (i < cnt) {
      (i % 5) match {
        case 0 =>
          res(i) = xp0
          xp0 += incr
        case 1 =>
          res(i) = xp1
          xp1 += incr
        case 2 =>
          res(i) = xp2
          xp2 += incr
        case 3 =>
          res(i) = xp3
          xp3 += incr
        case 4 =>
          res(i) = xp4
          xp4 -= incr
      }
      i += 1
    }
    res
  }
}
