package simianquant.intrinsicbench

import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

object Pow {

  @State(Scope.Benchmark)
  class ComputationalParameters {
    var baseLb = -5.0
    var baseUb = 5.0
    var powLb = -5.0
    var powUb = 5.0
    var cnt = 1000
    var barr = RangeGenerator(baseLb, baseUb, cnt)
    var parr = RangeGenerator(powLb, powUb, cnt)
  }

}

@BenchmarkMode(Array(Mode.SampleTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class Pow {

  import Pow._

  @Benchmark
  def baseline(cp: ComputationalParameters, bh: Blackhole): Unit = {
    var i = 0
    val barr = cp.barr
    val parr = cp.parr
    while (i < barr.length) {
      var base = barr(i)
      var j = 0
      while(j < parr.length){
        var pow = parr(j)
        bh.consume(base)
        bh.consume(pow)
        j += 1
      }
      i += 1
    }
  }

  @Benchmark
  def measurement(cp: ComputationalParameters, bh: Blackhole): Unit = {
    var i = 0
    val barr = cp.barr
    val parr = cp.parr
    while (i < barr.length) {
      var base = barr(i)
      var j = 0
      while(j < parr.length){
        var pow = parr(j)
        bh.consume(math.pow(base, pow))
        j += 1
      }
      i += 1
    }
  }

}
