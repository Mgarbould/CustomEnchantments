import io.papermc.paper.enchantments.EnchantmentRarity
import net.kyori.adventure.text.Component
import org.bukkit.enchantments.Enchantment
import org.bukkit.enchantments.EnchantmentTarget
import org.bukkit.entity.EntityCategory
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class VeinMinerEnchant : Enchantment(6969), Listener {
        override fun translationKey(): String {
                TODO("Not yet implemented")
        }

        override fun getName() = "VeinMiner"
        override fun getMaxLevel() = 1
        override fun getStartLevel() = 1
        override fun canEnchantItem(item: ItemStack) = true
        override fun displayName(level: Int): Component {
                TODO("Not yet implemented")
        }

        override fun isTradeable(): Boolean {
                TODO("Not yet implemented")
        }

        override fun isDiscoverable(): Boolean {
                TODO("Not yet implemented")
        }

        override fun getRarity(): EnchantmentRarity {
                TODO("Not yet implemented")
        }

        override fun getDamageIncrease(level: Int, entityCategory: EntityCategory): Float {
                TODO("Not yet implemented")
        }

        override fun getActiveSlots(): MutableSet<EquipmentSlot> {
                TODO("Not yet implemented")
        }

        override fun isTreasure() = false
        override fun isCursed() = false
        override fun getItemTarget() = EnchantmentTarget.TOOL
        override fun conflictsWith(other: Enchantment) = false
}

class VeinMiner : JavaPlugin(), Listener {
        override fun onEnable() {
                server.pluginManager.registerEvents(this, this)
                Enchantment.registerEnchantment(VeinMinerEnchant())
        }

        @EventHandler
        fun onBlockBreak(event: BlockBreakEvent) {
                val player = event.player
                val item = player.itemInHand
                if (item.enchantments.containsKey(VeinMinerEnchant())) {
                        val block = event.block
                        val vein = block.getDrops(item)
                        vein.forEach {
                                block.world.dropItem(block.location, it)
                        }
                        vein.clear()
                        block.breakNaturally(item)
                }
        }
} }