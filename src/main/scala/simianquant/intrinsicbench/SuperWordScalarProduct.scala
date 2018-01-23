package simianquant.intrinsicbench

import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._
import util.Random

object SuperWordScalarProduct {
  @State(Scope.Thread)
  class ComputationalParameters {
    var cnt = 1021
    var arr = new Array[Double](cnt)
    var ctr = 0
    val rnd = new Random(System.currentTimeMillis)
    while (ctr < cnt) {
      arr(ctr) = rnd.nextDouble()
      ctr += 1
    }
    val res = new Array[Double](cnt)
  }
}

@BenchmarkMode(Array(Mode.SampleTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class SuperWordScalarProduct {

  import SuperWordScalarProduct._

  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:+UseSuperWord"))
  def withSuperWord(cp: ComputationalParameters): Array[Double] = {
    var ctr = 0
    var b = 3.14
    while (ctr < cp.arr.length) {
      cp.res(ctr) = cp.arr(ctr) * b
      ctr += 1
    }
    cp.res
  }

  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:-UseSuperWord"))
  def withoutSuperWord(cp: ComputationalParameters): Array[Double] = {
    var ctr = 0
    var b = 3.14
    while (ctr < cp.arr.length) {
      cp.res(ctr) = cp.arr(ctr) * b
      ctr += 1
    }
    cp.res
  }

}
