package simianquant.intrinsicbench

import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

object Exponent {

  @State(Scope.Benchmark)
  class ComputationalParameters {
    var rangeLb = -10.0
    var rangeUb = 10.0
    var cnt = 1000000
    val arr = RangeGenerator(rangeLb, rangeUb, cnt)
  }

}

@BenchmarkMode(Array(Mode.SampleTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class Exponent {

  import Exponent._

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
      var x = math.exp(arr(i))
      bh.consume(x)
      i += 1
    }
  }

}
