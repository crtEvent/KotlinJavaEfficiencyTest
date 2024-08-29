package event_replay.kotlin

abstract class Invoker {
    private val commands: ArrayList<Command> = arrayListOf()

    fun save(): List<Command> = commands.toList().also{ commands.clear() }

    suspend fun restore(data: List<Command>): Boolean
        = if (commands.isNotEmpty() || data.isEmpty())
            false
        else
            data.all { on(it, true) }

    suspend fun on(cmd: Command, isReplay: Boolean = false): Boolean
        = if (cmd(this)) {
            if (!isReplay) commands.add(cmd)
            true
    } else false

    abstract suspend operator fun invoke(cmd: Command): Boolean
}

interface Command{

    suspend operator fun invoke(invoker: Invoker):Boolean = invoker(this)
}

class ItemAdd(
    val productNo: String,
    val name: String,
    val quantity: Int,
): Command {
}
class ItemRemove(
    val productNo: String,
): Command {

}

class Cart: Invoker() {
    data class Item (
        val productNo: String,
        val name: String,
        val quantity: Int,
    )

    private val items = mutableSetOf<Item>()

    private fun add(item: Item): Boolean = items.add(item)
    private fun remove(productNo: String): Boolean
        = if (items.size > 2) {
            items.find{ it.productNo == productNo }?.let{
                items.remove(it)
            } ?: false
    } else false

    override suspend fun invoke(cmd: Command): Boolean
        = when(cmd) {
            is ItemAdd -> add(Item(cmd.productNo, cmd.name, cmd.quantity))
            is ItemRemove -> remove(cmd.productNo)
            else -> false
        }

    override fun toString(): String = items.joinToString(separator = "\n") { it.toString() }
}

suspend fun main(){
    val commands = listOf(
        ItemAdd("1", "Laptop", 2),
        ItemAdd("2", "Headphones", 3),
        ItemAdd("3", "Backpack", 4),
        ItemRemove("2"),
        ItemAdd("4", "Water Bottle", 5),
        ItemRemove("1")
    )
    val cart = Cart()
    cart.restore(commands)
    println(cart)
    println()

    cart.on(ItemAdd("5", "Elderberry", 6))
    println(cart)
}
