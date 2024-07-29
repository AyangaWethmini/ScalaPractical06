// Define the case class for Item
case class Item(name: String, quantity: Int, price: Double)

// Create mutable maps for inventory
val inventory1 = scala.collection.mutable.Map[Int, Item]()
val inventory2 = scala.collection.mutable.Map[Int, Item]()

// Add items to inventory1
inventory1 += (1 -> Item("Pencil", 20, 15))
inventory1 += (2 -> Item("Pen", 31, 20))
inventory1 += (3 -> Item("Book", 20, 150))
inventory1 += (4 -> Item("Pencil-yellow", 13, 25))  // Changed key to 4 to avoid overwriting

// Add items to inventory2
inventory2 += (4 -> Item("Eraser", 20, 10))
inventory2 += (5 -> Item("Pen-red", 12, 20))
inventory2 += (6 -> Item("Scissor", 5, 200))
inventory2 += (7 -> Item("Ruler", 10, 25))  // Changed key to 7 to avoid overlap with inventory1

// Print Inventory 1
println("Inventory 01:")
inventory1.foreach { case (key, item) =>
  println(s"ID: $key, Name: ${item.name}, Price: ${item.price}, Quantity: ${item.quantity}")
}

// Print Inventory 2
println("Inventory 02:")
inventory2.foreach { case (key, item) =>
  println(s"ID: $key, Name: ${item.name}, Price: ${item.price}, Quantity: ${item.quantity}")
}
`