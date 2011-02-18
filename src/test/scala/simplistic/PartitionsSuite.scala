package simplistic

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

@org.junit.runner.RunWith(classOf[org.scalatest.junit.JUnitRunner])
class PartitionsSuite extends WordSpec with ShouldMatchers with TestUtil.CleanBefore with TestUtil.StopAndStartServer {

  import TestUtil._
  val testDomains = account.partitions("test1", "test2")

  "Partitions.create" should {

    "create the underlying domains" in {
      testDomains.create()
      account.domains.map(_.name).toSet should be === Set("test1", "test2")
    }

    "be idempotent" in {
      testDomains.create()
      testDomains.create()
      account.domains.map(_.name).toSet should be === Set("test1", "test2")
    }

    "create different underlying domains" in {
      account.partitions("foo", "fab").create()
      account.partitions("bar", "baz").create()
      account.domains.map(_.name).toSet should be === Set("foo", "fab", "bar", "baz")
    }

  }

  "Partitions.delete" should {

    "delete the underlying domains" in {
      testDomains.create()
      account.domains.map(_.name).toSet should be === Set("test1", "test2")
      testDomains.delete()
      account.domains.map(_.name).toSet should be === Set.empty
    }
  }

  "Partitions.metadata" should {

    "return metadata about the underlying domains" in {
      testDomains.create()
      val metas = testDomains.metadata
      metas.size should be === 2
      for(meta <- metas) {
        meta.itemCount should be === 0
        meta.attributeNameCount should be === 0
      }
    }
  }

  "Partitions.unique" should {
    "return unique item ids" in {
      val u1 = testDomains.unique
      val u2 = testDomains.unique
      u1.name should not be === (u2.name)
    }
  }


}

