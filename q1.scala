object q1 extends App {
  case class Product(name: String, quantity: Int, price: Double)

  val inventory1 = scala.collection.mutable.Map[Int, Product]()
  val inventory2 = scala.collection.mutable.Map[Int, Product]()

  inventory1 += (1 -> Product("Pencil", 20, 15))
  inventory1 += (2 -> Product("Pen", 31, 20))
  inventory1 += (3 -> Product("Book", 20, 150))

  inventory2 += (4 -> Product("Eraser", 20, 10))
  inventory2 += (2 -> Product("Pen-red", 12, 25))
  inventory2 += (5 -> Product("Scissor", 5, 200))

  def printProducts(inventory: Map[Int, Product]): Unit = {
    val productNames = inventory.values.map(_.name).toList
    println(productNames.mkString(", "))
  }

  def totalValue(inventory: Map[Int, Product]): Double = {
    inventory.values.map(item => item.quantity * item.price).sum
  }

  def isInventoryEmpty(inventory: Map[Int, Product]): Unit = {
    if (inventory.isEmpty) {
      println("Inventory is Empty")
    } else {
      println("Inventory is not Empty")
    }
  }

  def mergeInventories(inventory1: Map[Int, Product], inventory2: Map[Int, Product]): Map[Int, Product] = {
    (inventory1 ++ inventory2).map { case (id, product) =>
      val existingProduct = inventory1.get(id).orElse(inventory2.get(id))
      existingProduct match {
        case Some(existing) =>
          id -> Product(product.name, existing.quantity + product.quantity, Math.max(existing.price, product.price))
        case None =>
          id -> product
      }
    }
  }

  def printInventory(inventory: Map[Int, Product]): Unit = {
    println("Merged Inventory:")
    inventory.foreach { case (key, product) =>
      println(s"ID: $key, Name: ${product.name}, Price: ${product.price}, Quantity: ${product.quantity}")
    }
  }

  def checkProduct(inventory: Map[Int, Product], productIdToCheck: Int): Unit = {
    inventory.get(productIdToCheck) match {
      case Some(product) =>
        println(s"Product ID $productIdToCheck exists in inventory: Name: ${product.name}, Price: ${product.price}, Quantity: ${product.quantity}")
      case None =>
        println(s"Product ID $productIdToCheck does not exist in inventory.")
    }
  }


  val immutableInventory1 = inventory1.toMap
  val immutableInventory2 = inventory2.toMap

  // Execute functions
  println("Products in inventory 01:")
  printProducts(immutableInventory1)

  println(s"Total value in inventory 01: ${totalValue(immutableInventory1)}")


  isInventoryEmpty(immutableInventory1)

  val mergedInventory = mergeInventories(immutableInventory1, immutableInventory2)
  printInventory(mergedInventory)

  val productIdToCheck = 102
  println("Checking product ID 102:")
  checkProduct(immutableInventory1, productIdToCheck)
}
