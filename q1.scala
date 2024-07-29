object q1 extends App{
    case class Product(name: String, quantity: Int, price: Double)

    val inventory1 = scala.collection.mutable.Map[Int, Product]()
    val inventory2 = scala.collection.mutable.Map[Int, Product]()

    inventory1 += (1 -> Product("Pencil", 20, 15))
    inventory1 += (2 -> Product("Pen", 31, 20))
    inventory1 += (3 -> Product("Book", 20, 150))

    inventory2 += (4 -> Product("Eraser", 20, 10))
    inventory2 += (2 -> Product("Pen-red", 12, 25))
    inventory2 += (5 -> Product("Scissor", 5, 200))

    val productNamesInventory1 = inventory1.values.map(_.name).toList
    println(s"Product names in inventory1: ${productNamesInventory1.mkString(", ")}")

    val totalValueInventory1 = inventory1.values.map(item => item.quantity * item.price).sum
    println(s"Total value of inventory1: $$${totalValueInventory1}")

    val isInventory1Empty = inventory1.isEmpty
    println(s"Is inventory1 empty? $isInventory1Empty")


    val mergedInventory = (inventory1 ++ inventory2).map { case (id, product) =>
    val existingProduct = inventory1.get(id).orElse(inventory2.get(id))
    existingProduct match {
        case Some(existing) =>
        
        id -> Product(product.name, existing.quantity + product.quantity, Math.max(existing.price, product.price))
        case None =>
        id -> product
    }
    }


    println("Merged Inventory:")
    mergedInventory.foreach { case (key, product) =>
    println(s"ID: $key, Name: ${product.name}, Price: ${product.price}, Quantity: ${product.quantity}")
    }

    val productIdToCheck = 102
    inventory1.get(productIdToCheck) match {
    case Some(product) =>
        println(s"Product ID $productIdToCheck exists in inventory1: Name: ${product.name}, Price: ${product.price}, Quantity: ${product.quantity}")
    case None =>
        println(s"Product ID $productIdToCheck does not exist in inventory1.")
    }
}
