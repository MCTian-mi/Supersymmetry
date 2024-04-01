import globals.Globals

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
MIXER = recipemap('mixer')
FLUID_HEATER = recipemap('fluid_heater')
CENTRIFUGE = recipemap('centrifuge')
GREENHOUSE = recipemap('greenhouse')
COMPRESSOR = recipemap('compressor')
CHEMICAL_BATH = recipemap('chemical_bath')
PYROLYSE_OVEN = recipemap('pyrolyse_oven')
EXTRUDER = recipemap('extruder')
EXTRACTOR = recipemap('extractor')
MACERATOR = recipemap('macerator')

//REMOVALS

// Fertilizer * 4
mods.gregtech.mixer.removeByInput(30, [item('minecraft:dirt'), metaitem('dustWood') * 2, item('minecraft:sand') * 4], [fluid('water') * 1000])
mods.gregtech.blender.removeByInput(30, [item('minecraft:dirt'), metaitem('dustWood') * 2, item('minecraft:sand') * 4], [fluid('water') * 1000])
// Fertilizer Solution * 5000
mods.gregtech.mixer.removeByInput(16, [item('minecraft:dye', 15)], [fluid('water') * 5000])
mods.gregtech.blender.removeByInput(16, [item('minecraft:dye', 15)], [fluid('water') * 5000])
// Greenhouse Glass * 1
mods.gregtech.assembler.removeByInput(24, [metaitem('gregtechfoodoption:cupric_hydrogen_arsenite_dust'), item('gregtech:transparent_casing')], null)

//COTTON RECIPES

crafting.addShaped("susy:cotton_seeds", metaitem('seed.cotton') * 2, [
        [null, null, null],
        [null, null, null],
        [null, metaitem('gregtechfoodoption:seed.unknown'), metaitem('gregtechfoodoption:seed.unknown')]
])

EXTRUDER.recipeBuilder()
        .inputs(metaitem('crop.cotton') * 20)
        .notConsumable(ore('gearSteel') * 8)
        .outputs(metaitem('seed.cotton') * 5)
        .outputs(metaitem('stem.cotton') * 5)
        .outputs(metaitem('fiberCotton') * 40)
        .EUt(7)
        .duration(80)
        .buildAndRegister()

EXTRACTOR.recipeBuilder()
        .inputs(metaitem('seed.cotton'))
        .fluidOutputs(fluid('seed_oil') * 10)
        .EUt(2)
        .duration(32)
        .buildAndRegister()

crafting.addShapeless('string.cotton', item('minecraft:string'), [
    metaitem('threadCotton')
]) 

MACERATOR.recipeBuilder()
        .inputs(ore('threadCotton') * 2)
        .outputs(metaitem('dustCellulose'))
        .duration(100)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

//GREENHOUSE

CHEMICAL_BATH.recipeBuilder()
        .inputs(item('gregtech:transparent_casing'))
        .fluidInputs(fluid('dye_green') * 144)
        .outputs(item('gregtechfoodoption:gtfo_glass_casing'))
        .EUt(7)
        .duration(40)
        .buildAndRegister()

//FERTILIZER CHAIN

MIXER.recipeBuilder()
        .inputs(ore('nutrientNitrogen'))
        .inputs(ore('nutrientPotassium'))
        .inputs(ore('nutrientPhosphorous'))
        .outputs(metaitem('fertilizer') * 5)
        .EUt(30)
        .duration(100)
        .buildAndRegister();

MIXER.recipeBuilder()
        .inputs(ore('dustAmmoniumDihydrogenPhosphate') * 2)
        .inputs(ore('nutrientPotassium'))
        .outputs(metaitem('fertilizer') * 5)
        .EUt(30)
        .duration(100)
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(ore('dustSaltpeter') * 2)
        .inputs(ore('nutrientPhosphorous'))
        .outputs(metaitem('fertilizer') * 5)
        .EUt(30)
        .duration(100)
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(metaitem('bio_chaff') * 2)
        .inputs(ore('nutrientPhosphorous'))
        .outputs(metaitem('fertilizer') * 5)
        .EUt(30)
        .duration(100)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('fermented_biomass') * 1000)
        .inputs(ore('nutrientPhosphorous'))
        .outputs(metaitem('fertilizer') * 5)
        .EUt(30)
        .duration(100)
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(metaitem('fertilizer') * 2)
        .fluidInputs(fluid('water') * 1000)
        .fluidOutputs(fluid('gtfo_fertilizer_solution') * 1000)
        .EUt(30)
        .duration(60)
        .buildAndRegister()



COMPRESSOR.recipeBuilder()
        .inputs(ore('treeLeaves') * 16)
        .outputs(metaitem('plant_ball'))
        .EUt(2)
        .duration(300)
        .buildAndRegister()

PYROLYSE_OVEN.recipeBuilder()
        .inputs(item('minecraft:sugar') * 24)
        .outputs(metaitem('dustCarbon') * 6)
        .fluidOutputs(fluid('steam') * 6000)
        .duration(320)
        .EUt(64)
        .buildAndRegister()

// WEED KILLERS & PESTICIDES

MIXER.recipeBuilder()
        .fluidInputs(fluid('acetic_acid') * 100)
        .fluidInputs(fluid('gtfo_sodium_stearate') * 100)
        .fluidInputs(fluid('salt_water') * 1000)
        .fluidOutputs(fluid('weed_killer') * 1000)
        .EUt(30)
        .duration(60)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('seed_oil') * 100)
        .fluidInputs(fluid('gtfo_sodium_stearate') * 100)
        .fluidInputs(fluid('water') * 1000)
        .fluidOutputs(fluid('pesticide') * 1000)
        .EUt(30)
        .duration(60)
        .buildAndRegister()

// GREENHOUSE GASES

MIXER.recipeBuilder()
        .fluidInputs(fluid('air') * 1000)
        .fluidInputs(fluid('carbon_dioxide') * 100)
        .fluidOutputs(fluid('greenhouse_gases') * 1000)
        .EUt(30)
        .duration(20)
        .buildAndRegister()

FLUID_HEATER.recipeBuilder()
        .fluidInputs(fluid('greenhouse_gases') * 1000)
        .fluidOutputs(fluid('warm_greenhouse_gases') * 1000)
        .EUt(30)
        .duration(20)
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('water') * 40)
        .fluidInputs(fluid('greenhouse_gases') * 1000)
        .fluidOutputs(fluid('cool_greenhouse_gases') * 1000)
        .EUt(30)
        .duration(20)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('greenhouse_gases') * 1000)
        .fluidInputs(fluid('water') * 100)
        .fluidOutputs(fluid('humid_greenhouse_gases') * 1000)
        .EUt(30)
        .duration(20)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('greenhouse_gases') * 1000)
        .fluidInputs(fluid('sulfuric_acid') * 20)
        .fluidOutputs(fluid('dry_greenhouse_gases') * 1000)
        .EUt(30)
        .duration(20)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('warm_greenhouse_gases') * 1000)
        .fluidInputs(fluid('water') * 100)
        .fluidOutputs(fluid('warm_humid_greenhouse_gases') * 1000)
        .EUt(30)
        .duration(20)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('warm_greenhouse_gases') * 1000)
        .fluidInputs(fluid('sulfuric_acid') * 20)
        .fluidOutputs(fluid('warm_dry_greenhouse_gases') * 1000)
        .EUt(30)
        .duration(20)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('cool_greenhouse_gases') * 1000)
        .fluidInputs(fluid('water') * 100)
        .fluidOutputs(fluid('cool_humid_greenhouse_gases') * 1000)
        .EUt(30)
        .duration(20)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('cool_greenhouse_gases') * 1000)
        .fluidInputs(fluid('sulfuric_acid') * 20)
        .fluidOutputs(fluid('cool_dry_greenhouse_gases') * 1000)
        .EUt(30)
        .duration(20)
        .buildAndRegister()

//THIS IS FOR GREENHOUSES IN OUTER SPACE

MIXER.recipeBuilder()
        .fluidInputs(fluid('nitrogen') * 780)
        .fluidInputs(fluid('oxygen') * 220)
        .fluidOutputs(fluid('earth_like_air') * 1000)
        .EUt(30)
        .duration(20)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('earth_like_air') * 1000)
        .fluidInputs(fluid('carbon_dioxide') * 100)
        .fluidOutputs(fluid('greenhouse_gases') * 1000)
        .EUt(30)
        .duration(20)
        .buildAndRegister()

//TREES
GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:sapling'))
        .fluidInputs(fluid('greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('minecraft:log') * 6)
        .outputs(item('minecraft:leaves') * 12)
        .outputs(item('minecraft:sapling') * 2)
        .outputs(item('minecraft:apple') * 1)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:sapling', 1))
        .fluidInputs(fluid('cool_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('minecraft:log', 1) * 8)
        .outputs(item('minecraft:leaves', 1) * 14)
        .outputs(item('minecraft:sapling', 1) * 2)
        .EUt(30)
        .duration(480)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:sapling', 2))
        .fluidInputs(fluid('greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('minecraft:log', 2) * 6)
        .outputs(item('minecraft:leaves', 2) * 12)
        .outputs(item('minecraft:sapling', 2) * 2)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:sapling', 3))
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 8)
        .outputs(item('minecraft:log', 3) * 24)
        .outputs(item('minecraft:leaves', 3) * 40)
        .outputs(item('minecraft:sapling', 3) * 5)
        .EUt(30)
        .duration(1440)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:sapling', 4))
        .fluidInputs(fluid('warm_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('minecraft:log2') * 6)
        .outputs(item('minecraft:leaves2') * 12)
        .outputs(item('minecraft:sapling', 4) * 2)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:sapling', 5))
        .fluidInputs(fluid('cool_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 8)
        .outputs(item('minecraft:log2', 1) * 24)
        .outputs(item('minecraft:leaves2', 1) * 40)
        .outputs(item('minecraft:sapling', 5) * 5)
        .outputs(item('minecraft:apple') * 2)
        .EUt(30)
        .duration(1440)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtech:rubber_sapling'))
        .fluidInputs(fluid('greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtech:rubber_log') * 6)
        .outputs(item('gregtech:rubber_leaves') * 12)
        .outputs(metaitem('rubber_drop') * 4)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

//FRUIT TREES

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0'))
        .circuitMeta(1)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_0') * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_0') * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0') * 2)
        .outputs(metaitem('gregtechfoodoption:food.banana') * 4)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0'))
        .circuitMeta(2)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_0') * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_0') * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0') * 2)
        .outputs(metaitem('gregtechfoodoption:food.banana') * 8)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 2))
        .circuitMeta(1)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_0', 4) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_0', 4) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 2) * 2)
        .outputs(metaitem('gregtechfoodoption:food.orange') * 4)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 2))
        .circuitMeta(2)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_0', 4) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_0', 4) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 2) * 2)
        .outputs(metaitem('gregtechfoodoption:food.orange') * 8)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 4))
        .circuitMeta(1)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_0', 8) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_0', 8) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 4) * 2)
        .outputs(metaitem('gregtechfoodoption:food.mango') * 4)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 4))
        .circuitMeta(2)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_0', 8) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_0', 8) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 4) * 2)
        .outputs(metaitem('gregtechfoodoption:food.mango') * 8)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 6))
        .circuitMeta(1)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_0', 12) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_0', 12) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 6) * 2)
        .outputs(metaitem('gregtechfoodoption:food.apricot') * 4)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 6))
        .circuitMeta(2)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_0', 12) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_0', 12) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 6) * 2)
        .outputs(metaitem('gregtechfoodoption:food.apricot') * 8)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 8))
        .circuitMeta(1)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_1') * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_1') * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 8) * 2)
        .outputs(metaitem('gregtechfoodoption:food.lemon') * 5)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 8))
        .circuitMeta(2)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_1') * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_1') * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 8) * 2)
        .outputs(metaitem('gregtechfoodoption:food.lemon') * 10)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 10))
        .circuitMeta(1)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_1', 4) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_1', 4) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 10) * 2)
        .outputs(metaitem('gregtechfoodoption:food.lime') * 6)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 10))
        .circuitMeta(2)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_1', 4) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_1', 4) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 10) * 2)
        .outputs(metaitem('gregtechfoodoption:food.lime') * 12)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 12))
        .circuitMeta(1)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_1', 8) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_1', 8) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 12) * 2)
        .outputs(metaitem('gregtechfoodoption:crop.olive') * 20)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 12))
        .circuitMeta(2)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_1', 8) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_1', 8) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 12) * 2)
        .outputs(metaitem('gregtechfoodoption:crop.olive') * 40)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 14))
        .circuitMeta(1)
        .fluidInputs(fluid('greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_1', 12) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_1', 12) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 14) * 2)
        .outputs(metaitem('gregtechfoodoption:crop.olive') * 20)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_0', 14))
        .circuitMeta(2)
        .fluidInputs(fluid('greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_1', 12) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_1', 12) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_0', 14) * 2)
        .outputs(metaitem('gregtechfoodoption:crop.olive') * 40)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_1'))
        .circuitMeta(1)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_2') * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_2') * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_1') * 2)
        .outputs(metaitem('gregtechfoodoption:component.nutmeg') * 10)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_1'))
        .circuitMeta(2)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_2') * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_2') * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_1') * 2)
        .outputs(metaitem('gregtechfoodoption:component.nutmeg') * 20)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_1', 2))
        .circuitMeta(2)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_2', 4) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_2', 4) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_1', 2) * 2)
        .outputs(metaitem('component.coconut') * 2)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('gregtechfoodoption:gtfo_sapling_1', 2))
        .circuitMeta(2)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('gregtechfoodoption:gtfo_log_2', 4) * 6)
        .outputs(item('gregtechfoodoption:gtfo_leaves_2', 4) * 12)
        .outputs(item('gregtechfoodoption:gtfo_sapling_1', 2) * 2)
        .outputs(metaitem('component.coconut') * 4)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

def generateGreenhouseTreeRecipes(String gasType, sapling, leaves, wood) {
    GREENHOUSE.recipeBuilder()
            .notConsumable(sapling)
            .circuitMeta(1)
            .fluidInputs(fluid(gasType) * 10000)
            .fluidInputs(fluid('water') * 5000)
            .inputs(metaitem('fertilizer') * 4)
            .outputs(wood * 6)
            .outputs(leaves * 12)
            .outputs(sapling * 2)
            .EUt(30)
            .duration(360)
            .buildAndRegister()
}

GREENHOUSE.recipeBuilder()
        .notConsumable(item('biomesoplenty:sapling_0', 2))
        .circuitMeta(1)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 4)
        .outputs(item('biomesoplenty:bamboo') * 12)
        .EUt(30)
        .duration(360)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('biomesoplenty:sapling_1', 7))
        .circuitMeta(1)
        .fluidInputs(fluid('greenhouse_gases') * 20000)
        .fluidInputs(fluid('water') * 20000)
        .inputs(metaitem('fertilizer') * 16)
        .outputs(item('biomesoplenty:log_0', 4) * 150)
        .outputs(item('minecraft:leaves') * 64)
        .EUt(30)
        .duration(6000)
        .buildAndRegister()

generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_0', 1), item('biomesoplenty:leaves_0', 1), item('minecraft:log2', 1))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_0', 0), item('biomesoplenty:leaves_0', 0), item('minecraft:log', 2))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_0', 3), item('biomesoplenty:leaves_0', 11), item('biomesoplenty:log_1', 5))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_0', 4), item('biomesoplenty:leaves_1', 8), item('biomesoplenty:log_0', 6))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_0', 5), item('biomesoplenty:leaves_1', 9), item('biomesoplenty:log_4', 5))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_0', 6), item('biomesoplenty:leaves_1', 10), item('biomesoplenty:log_0', 7))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_0', 7), item('biomesoplenty:leaves_1', 11), item('biomesoplenty:log_1', 4))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_1', 0), item('biomesoplenty:leaves_2', 8), item('minecraft:log'))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_1', 1), item('biomesoplenty:leaves_2', 1), item('biomesoplenty:log_0', 5))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_1', 2), item('biomesoplenty:leaves_2', 10), item('biomesoplenty:log_0', 5))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_1', 3), item('biomesoplenty:leaves_2', 3), item('minecraft:log'))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_1', 5), item('biomesoplenty:leaves_3', 1), item('minecraft:log'))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_1', 6), item('biomesoplenty:leaves_3', 10), item('biomesoplenty:log_3', 4))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_2', 0), item('biomesoplenty:leaves_4', 8), item('biomesoplenty:log_1', 6))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_2', 1), item('biomesoplenty:leaves_4', 9), item('biomesoplenty:log_1', 7))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_2', 2), item('biomesoplenty:leaves_4', 10), item('biomesoplenty:log_2', 4))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_2', 3), item('biomesoplenty:leaves_4', 11), item('biomesoplenty:log_2', 5))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_2', 4), item('biomesoplenty:leaves_5', 8), item('biomesoplenty:log_2', 6))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_2', 5), item('biomesoplenty:leaves_5', 9), item('biomesoplenty:log_3', 5))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_2', 6), item('biomesoplenty:leaves_5', 10), item('biomesoplenty:log_3', 6))
generateGreenhouseTreeRecipes('greenhouse_gases', item('biomesoplenty:sapling_2', 7), item('biomesoplenty:leaves_5', 11), item('biomesoplenty:log_3', 7))

//VANILLA CROPS

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:cactus'))
        .notConsumable(item('minecraft:sand'))
        .fluidInputs(fluid('warm_dry_greenhouse_gases') * 10000)
        .outputs(item('minecraft:cactus') * 12)
        .EUt(30)
        .duration(2400)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:dye', 3))
        .notConsumable(item('minecraft:log', 3))
        .circuitMeta(1)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 2)
        .outputs(item('minecraft:dye', 3) * 15)
        .EUt(30)
        .duration(2400)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:dye', 3))
        .notConsumable(item('minecraft:log', 3))
        .circuitMeta(2)
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .inputs(metaitem('fertilizer') * 2)
        .outputs(item('minecraft:dye', 3) * 30)
        .EUt(30)
        .duration(2400)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:reeds'))
        .notConsumable(item('minecraft:sand'))
        .fluidInputs(fluid('warm_humid_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 2)
        .outputs(item('minecraft:reeds') * 40)
        .EUt(30)
        .duration(2400)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:wheat_seeds'))
        .circuitMeta(1)
        .fluidInputs(fluid('greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 2)
        .outputs(item('minecraft:wheat_seeds') * 25)
        .outputs(item('minecraft:wheat') * 30)
        .EUt(30)
        .duration(1200)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:wheat_seeds'))
        .circuitMeta(2)
        .fluidInputs(fluid('greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('weed_killer') * 500)
        .inputs(metaitem('fertilizer') * 2)
        .outputs(item('minecraft:wheat_seeds') * 50)
        .outputs(item('minecraft:wheat') * 60)
        .EUt(30)
        .duration(1200)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:wheat_seeds'))
        .circuitMeta(3)
        .fluidInputs(fluid('greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .inputs(metaitem('fertilizer') * 2)
        .outputs(item('minecraft:wheat_seeds') * 50)
        .outputs(item('minecraft:wheat') * 60)
        .EUt(30)
        .duration(1200)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:wheat_seeds'))
        .circuitMeta(4)
        .fluidInputs(fluid('greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('pesticide') * 500)
        .fluidInputs(fluid('weed_killer') * 500)
        .inputs(metaitem('fertilizer') * 2)
        .outputs(item('minecraft:wheat_seeds') * 75)
        .outputs(item('minecraft:wheat') * 90)
        .EUt(30)
        .duration(1200)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:brown_mushroom'))
        .circuitMeta(1)
        .fluidInputs(fluid('cool_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 2)
        .outputs(item('minecraft:brown_mushroom') * 10)
        .EUt(30)
        .duration(1200)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:brown_mushroom'))
        .circuitMeta(2)
        .fluidInputs(fluid('cool_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('weed_killer') * 500)
        .inputs(metaitem('fertilizer') * 2)
        .outputs(item('minecraft:brown_mushroom') * 20)
        .EUt(30)
        .duration(1200)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:red_mushroom'))
        .circuitMeta(1)
        .fluidInputs(fluid('cool_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .inputs(metaitem('fertilizer') * 2)
        .outputs(item('minecraft:red_mushroom') * 10)
        .EUt(30)
        .duration(1200)
        .buildAndRegister()

GREENHOUSE.recipeBuilder()
        .notConsumable(item('minecraft:red_mushroom'))
        .circuitMeta(2)
        .fluidInputs(fluid('cool_greenhouse_gases') * 10000)
        .fluidInputs(fluid('water') * 5000)
        .fluidInputs(fluid('weed_killer') * 500)
        .inputs(metaitem('fertilizer') * 2)
        .outputs(item('minecraft:red_mushroom') * 20)
        .EUt(30)
        .duration(1200)
        .buildAndRegister()

def generateGreenhouseCropRecipes(String gasType, input, output, int multiplier) {
    GREENHOUSE.recipeBuilder()
            .notConsumable(input)
            .circuitMeta(1)
            .fluidInputs(fluid(gasType) * 10000)
            .fluidInputs(fluid('water') * 5000)
            .inputs(metaitem('fertilizer') * 2)
            .outputs(output * multiplier)
            .EUt(30)
            .duration(1200)
            .buildAndRegister()

    GREENHOUSE.recipeBuilder()
            .notConsumable(input)
            .circuitMeta(2)
            .fluidInputs(fluid(gasType) * 10000)
            .fluidInputs(fluid('water') * 5000)
            .fluidInputs(fluid('weed_killer') * 500)
            .inputs(metaitem('fertilizer') * 2)
            .outputs(output * (multiplier * 2))
            .EUt(30)
            .duration(1200)
            .buildAndRegister()

    GREENHOUSE.recipeBuilder()
            .notConsumable(input)
            .circuitMeta(3)
            .fluidInputs(fluid(gasType) * 10000)
            .fluidInputs(fluid('water') * 5000)
            .fluidInputs(fluid('pesticide') * 500)
            .inputs(metaitem('fertilizer') * 2)
            .outputs(output * (multiplier * 2))
            .EUt(30)
            .duration(1200)
            .buildAndRegister()

    GREENHOUSE.recipeBuilder()
            .notConsumable(input)
            .circuitMeta(4)
            .fluidInputs(fluid(gasType) * 10000)
            .fluidInputs(fluid('water') * 5000)
            .fluidInputs(fluid('pesticide') * 500)
            .fluidInputs(fluid('weed_killer') * 500)
            .inputs(metaitem('fertilizer') * 2)
            .outputs(output * (multiplier * 3))
            .EUt(30)
            .duration(1200)
            .buildAndRegister()
}

generateGreenhouseCropRecipes('greenhouse_gases', item('minecraft:potato'), item('minecraft:potato'), 30)
generateGreenhouseCropRecipes('greenhouse_gases', item('minecraft:carrot'), item('minecraft:carrot'), 30)
generateGreenhouseCropRecipes('greenhouse_gases', item('minecraft:beetroot_seeds'), item('minecraft:beetroot'), 30)
generateGreenhouseCropRecipes('warm_greenhouse_gases', item('minecraft:melon_seeds'), item('minecraft:melon_block'), 10)
generateGreenhouseCropRecipes('cool_greenhouse_gases', item('minecraft:pumpkin_seeds'), item('minecraft:pumpkin'), 10)

//GTFO CROPS

generateGreenhouseCropRecipes('warm_greenhouse_gases', metaitem('gregtechfoodoption:seed.soy'), metaitem('gregtechfoodoption:component.soybean'), 20)
generateGreenhouseCropRecipes('greenhouse_gases', metaitem('gregtechfoodoption:seed.tomato'), metaitem('gregtechfoodoption:crop.tomato'), 20)
generateGreenhouseCropRecipes('warm_greenhouse_gases', metaitem('gregtechfoodoption:seed.cucumber'), metaitem('gregtechfoodoption:crop.cucumber'), 20)
generateGreenhouseCropRecipes('greenhouse_gases', metaitem('gregtechfoodoption:seed.onion'), metaitem('gregtechfoodoption:crop.onion'), 20)
generateGreenhouseCropRecipes('warm_humid_greenhouse_gases', metaitem('gregtechfoodoption:seed.grape'), metaitem('gregtechfoodoption:food.grapes'), 20)
generateGreenhouseCropRecipes('warm_humid_greenhouse_gases', metaitem('gregtechfoodoption:seed.coffee'), metaitem('gregtechfoodoption:seed.coffee'), 20)
generateGreenhouseCropRecipes('greenhouse_gases', metaitem('gregtechfoodoption:seed.pea'), metaitem('gregtechfoodoption:seed.pea'), 20)
generateGreenhouseCropRecipes('greenhouse_gases', metaitem('gregtechfoodoption:seed.bean'), metaitem('gregtechfoodoption:seed.bean'), 20)
generateGreenhouseCropRecipes('cool_humid_greenhouse_gases', metaitem('gregtechfoodoption:seed.horseradish'), metaitem('gregtechfoodoption:component.horseradish'), 20)
generateGreenhouseCropRecipes('warm_dry_greenhouse_gases', metaitem('gregtechfoodoption:seed.oregano'), metaitem('gregtechfoodoption:component.oregano'), 20)
generateGreenhouseCropRecipes('cool_greenhouse_gases', metaitem('gregtechfoodoption:seed.garlic'), metaitem('gregtechfoodoption:seed.garlic'), 20)
generateGreenhouseCropRecipes('warm_humid_greenhouse_gases', metaitem('gregtechfoodoption:seed.basil'), metaitem('gregtechfoodoption:component.basil'), 20)
generateGreenhouseCropRecipes('warm_humid_greenhouse_gases', metaitem('gregtechfoodoption:seed.aubergine'), metaitem('gregtechfoodoption:crop.aubergine'), 20)
generateGreenhouseCropRecipes('warm_greenhouse_gases', metaitem('gregtechfoodoption:component.corn.ear'), metaitem('gregtechfoodoption:component.corn.ear'), 20)
generateGreenhouseCropRecipes('greenhouse_gases', metaitem('gregtechfoodoption:seed.artichoke'), metaitem('gregtechfoodoption:component.artichoke'), 20)
generateGreenhouseCropRecipes('warm_humid_greenhouse_gases', metaitem('gregtechfoodoption:component.black_pepper'), metaitem('gregtechfoodoption:component.black_pepper'), 20)
generateGreenhouseCropRecipes('warm_humid_greenhouse_gases', metaitem('gregtechfoodoption:component.rice'), metaitem('gregtechfoodoption:component.rice'), 20)

//CUSTOM CROPS

generateGreenhouseCropRecipes('warm_humid_greenhouse_gases', metaitem('seed.cotton'), metaitem('crop.cotton'), 20)
