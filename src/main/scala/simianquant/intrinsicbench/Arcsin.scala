package simianquant.intrinsicbench

import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

object Arcsin {

  @State(Scope.Benchmark)
  class ComputationalParameters {
    var rangeLb = -1
    var rangeUb = 1
    var cnt = 1000000
    var arr = RangeGenerator(rangeLb, rangeUb, cnt)
  }

}

@BenchmarkMode(Array(Mode.SampleTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class Arcsin {

  import Arcsin._

  @Benchmark
  def baseline(cp: ComputationalParameters, bh: Blackhole): Unit = {
    var i = 0
    val arr = cp.arr
    while (i < arr.length) {
      var x = arr(i)
      bh.consume(x)
      i += 1
    }
  }

  @Benchmark
  def measurement(cp: ComputationalParameters, bh: Blackhole): Unit = {
    var i = 0
    val arr = cp.arr
    while (i < arr.length) {
      var x = math.asin(arr(i))
      bh.consume(x)
      i += 1
    }
  }

}
