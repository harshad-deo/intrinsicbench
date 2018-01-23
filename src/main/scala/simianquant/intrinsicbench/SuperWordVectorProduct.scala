package simianquant.intrinsicbench

import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._
import util.Random

object SuperWordVectorProduct {
  @State(Scope.Thread)
  class ComputationalParameters {
    var cnt = 1021
    var a1 = new Array[Double](cnt)
    var a2 = new Array[Double](cnt)
    var ctr = 0
    val rnd = new Random(System.currentTimeMillis)
    while (ctr < cnt) {
      a1(ctr) = rnd.nextDouble()
      a2(ctr) = rnd.nextDouble()
      ctr += 1
    }
    val res = new Array[Double](cnt)
  }
}

@BenchmarkMode(Array(Mode.SampleTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class SuperWordVectorProduct {

  import SuperWordVectorProduct._

  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:+UseSuperWord"))
  def withSuperWord(cp: ComputationalParameters): Array[Double] = {
    var ctr = 0
    var b1 = 3.14
    var b2 = -2.72
    while (ctr < cp.a1.length) {
      cp.res(ctr) = cp.a1(ctr) * b1 + cp.a2(ctr) * b2
      ctr += 1
    }
    cp.res
  }

  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:-UseSuperWord"))
  def withoutSuperWord(cp: ComputationalParameters): Array[Double] = {
    var ctr = 0
    var b1 = 3.14
    var b2 = -2.72
    while (ctr < cp.a1.length) {
      cp.res(ctr) = cp.a1(ctr) * b1 + cp.a2(ctr) * b2
      ctr += 1
    }
    cp.res
  }

}
