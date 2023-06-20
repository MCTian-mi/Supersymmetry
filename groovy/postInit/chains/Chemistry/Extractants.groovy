import static globals.Globals.*

CSTR = recipemap('continuous_stirred_tank_reactor')
TBR = recipemap('trickle_bed_reactor')
FBR = recipemap('fixed_bed_reactor')
BCR = recipemap('bubble_column_reactor')
BR = recipemap('batch_reactor')
POLYMERIZATION = recipemap('polymerization_tank')
FLUIDIZEDBR = recipemap('fluidized_bed_reactor')
DISTILLATION_TOWER = recipemap('distillation_tower')
DISTILLERY = recipemap('distillery')
ROASTER = recipemap('roaster')
CRYSTALLIZER = recipemap('crystallizer')
MIXER = recipemap('mixer')
DRYER = recipemap('dryer')
CHEMICAL_BATH = recipemap('chemical_bath')
CENTRIFUGE = recipemap('centrifuge')
PYROLYSE = recipemap('pyrolyse_oven')
LCR = recipemap('large_chemical_reactor')
EBF = recipemap('electric_blast_furnace')
VULCANIZER = recipemap('vulcanizing_press')
ALLOY_SMELTER = recipemap('alloy_smelter')
ARC_FURNACE = recipemap('arc_furnace')
VACUUM_DT = recipemap('vacuum_distillation')
AUTOCLAVE = recipemap('autoclave')
COMPRESSOR = recipemap('compressor')
ASSEMBLER = recipemap('assembler')
ELECTROLYZER = recipemap('electrolyzer')
ELECTROLYTIC_CELL = recipemap('electrolytic_cell')
REACTION_FURNACE = recipemap('reaction_furnace')
ELECTROMAGNETIC_SEPARATOR = recipemap('electromagnetic_separator')
PSA = recipemap('pressure_swing_adsorption')
SINTERING_OVEN = recipemap('sintering_oven')

//LIXIVANTS
//SODIUM CYANIDE
FBR.recipeBuilder()
    .notConsumable(metaitem('mv_catalyst_bed_oxidation'))
    .fluidInputs(fluid('methane') * 100)
    .fluidInputs(fluid('ammonia') * 100)
    .fluidInputs(fluid('oxygen') * 150)
    .fluidOutputs(fluid('hydrogen_cyanide') * 100)
    .fluidOutputs(fluid('water') * 300)
    .duration(6)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

BCR.recipeBuilder()
    .fluidInputs(fluid('sodium_hydroxide_solution') * 50)
    .fluidInputs(fluid('hydrogen_cyanide') * 50)
    .fluidOutputs(fluid('sodium_cyanide_solution') * 50)
    .duration(10)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

DISTILLERY.recipeBuilder()
    .fluidInputs(fluid('sodium_cyanide_solution') * 1000)
    .outputs(ore('dustSodiumCyanide').first() * 3)
    .fluidOutputs(fluid('water') * 2000)
    .duration(60)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

//SODIUM THIOSULFATE
BCR.recipeBuilder() 
    .fluidInputs(fluid('soda_ash_solution') * 50)
    .fluidInputs(fluid('sulfur_dioxide') * 50)
    .fluidOutputs(fluid('sodium_sulfite_solution') * 50)
    .fluidOutputs(fluid('carbon_dioxide') * 50)
    .duration(6)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

BR.recipeBuilder()
    .inputs(ore('dustSulfur'))
    .fluidInputs(fluid('sodium_sulfite_solution') * 1000)
    .fluidOutputs(fluid('sodium_thiosulfate_solution') * 1000)
    .duration(120)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

DISTILLERY.recipeBuilder() 
    .fluidInputs(fluid('sodium_thiosulfate_solution') * 1000)
    .outputs(metaitem('dustSodiumThiosulfate') * 7)
    .fluidOutputs(fluid('water') * 1000)
    .duration(120)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

//FLOTATION AGENTS
//FATTY ACIDS
ROASTER.recipeBuilder()
    .fluidInputs(fluid('steam') * 3000)
    .fluidInputs(fluid('seed_oil') * 1000)
    .fluidOutputs(fluid('glycerol') * 1000)
    .fluidOutputs(fluid('fatty_acid_solution') * 3000)
    .EUt(30)
    .duration(200)
    .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
    .fluidInputs(fluid('fatty_acid_solution') * 1000)
    .fluidInputs(fluid('methanol') * 1000)
    .inputs(metaitem('dustUrea'))
    .fluidOutputs(fluid('oleic_acid_solution') * 1000)
    .EUt(30)
    .duration(200)
    .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
    .fluidInputs(fluid('oleic_acid_solution') * 1000)
    .outputs(metaitem('dustOleicAcid') * 18)
    .fluidOutputs(fluid('methanol') * 1000)
    .EUt(30)
    .duration(200)
    .buildAndRegister()

MIXER.recipeBuilder()
    .fluidInputs(fluid('sodium_hydroxide_solution') * 1000)
    .inputs(metaitem('dustOleicAcid') * 18)
    .fluidOutputs(fluid('alkaline_sodium_oleate_solution') * 1000)
    .EUt(30)
    .duration(80)
    .buildAndRegister()

//XANTHATES
BATCH_REACTOR.recipeBuilder()
    .inputs(metaitem('dustSodium'))
    .fluidInputs(fluid('ethanol') * 2000)
    .fluidOutputs(fluid('hydrogen') * 1000)
    .fluidOutputs(fluid('sodium_ethoxide_solution') * 1000)
    .EUt(Globals.voltAmps[3])
    .duration(80)
    .buildAndRegister()

CSTR.recipeBuilder()
    .fluidInputs(fluid('sodium_ethoxide_solution') * 50)
    .fluidInputs(fluid('carbon_disulfide') * 50)
    .fluidOutputs(fluid('sodium_ethyl_xanthate_solution') * 50)
    .EUt(Globals.voltAmps[3])
    .duration(4)
    .buildAndRegister()

DISTILLERY.recipeBuilder()
    .fluidInputs(fluid('sodium_ethyl_xanthate_solution') * 1000)
    .outputs(metaitem('dustSodiumEthylXanthate'))
    .fluidOutputs(fluid('ethanol') * 1000)
    .EUt(Globals.voltAmps[3])
    .duration(80)
    .buildAndRegister()

//LIQUID-LIQUID EXTRACTANTS
//TODGA
CSTR.recipeBuilder()
        .fluidInputs(fluid('diethylene_glycol') * 50)
        .fluidInputs(fluid('nitric_acid') * 400)
        .fluidInputs(fluid('ethanol') * 50)
        .fluidOutputs(fluid('diglycolic_acid_solution') * 150)
        .fluidOutputs(fluid('nitrogen_dioxide') * 200)
        .duration(3)
        .EUt(120)
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('diglycolic_acid_solution') * 3000)
        .fluidOutputs(fluid('diglycolic_acid') * 1000)
        .fluidOutputs(fluid('ethanol') * 1000)
        .fluidOutputs(fluid('nitric_acid') * 4000)
        .fluidOutputs(fluid('wastewater') * 2000)
        .duration(160)
        .EUt(120)
        .buildAndRegister()

CSTR.recipeBuilder()
        .fluidInputs(fluid('diglycolic_acid') * 50)
        .fluidInputs(fluid('thionyl_chloride') * 50)
        .fluidInputs(fluid('pyridine') * 50)
        .fluidOutputs(fluid('diluted_chloroacetic_anhydride') * 150)
        .fluidOutputs(fluid('sulfur_dioxide') * 50)
        .duration(3)
        .EUt(120)
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('diluted_chloroacetic_anhydride') * 3000)
        .outputs(metaitem('dustChloroaceticAnhydride') * 13)
        .fluidOutputs(fluid('pyridine') * 1000)
        .fluidOutputs(fluid('water') * 1000)
        .duration(160)
        .EUt(120)
        .buildAndRegister()

REACTION_FURNACE.recipeBuilder()
        .fluidInputs(fluid('ethanol') * 2000)
        .notConsumable(metaitem('catalystBedAlumina'))
        .fluidOutputs(fluid('impure_diethyl_ether') * 1000)
        .duration(160)
        .EUt(120)
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('impure_diethyl_ether') * 1000)
        .fluidOutputs(fluid('diethyl_ether') * 900)
        .fluidOutputs(fluid('gtfo_acetaldehyde') * 200)
        .duration(160)
        .EUt(120)
        .buildAndRegister()

BR.recipeBuilder()
        .fluidInputs(fluid('triethylaluminium') * 909)
        .fluidInputs(fluid('ethylene') * 23238)
        .fluidInputs(fluid('diethyl_ether') * 1000)
        .fluidOutputs(fluid('trialkylaluminium_mixture') * 1000)
        .duration(400)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

ROASTER.recipeBuilder()
        .fluidInputs(fluid('trialkylaluminium_mixture') * 1000)
        .fluidInputs(fluid('oxygen') * 3000)
        .fluidOutputs(fluid('aluminium_alkoxide_solution') * 1000)
        .duration(500)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

DISTILLERY.recipeBuilder()
        .fluidInputs(fluid('aluminium_alkoxide_solution') * 1000)
        .outputs(metaitem('dustAluminiumAlkoxideMixture'))
        .fluidOutputs(fluid('diethyl_ether') * 1000)
        .duration(500)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

BR.recipeBuilder()
        .inputs(metaitem('dustAluminiumAlkoxideMixture'))
        .fluidInputs(fluid('water') * 3000)
        .outputs(metaitem('dustAluminiumHydroxide') * 7)
        .fluidOutputs(fluid('ziegler_alcohol_mixture') * 3000)
        .duration(500)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('ziegler_alcohol_mixture') * 1000)
        .fluidOutputs(fluid('ethanol') * 5)
        .fluidOutputs(fluid('n_butanol') * 34)
        .fluidOutputs(fluid('n_hexanol') * 95)
        .fluidOutputs(fluid('n_octanol') * 161)
        .fluidOutputs(fluid('n_decanol') * 195)
        .fluidOutputs(fluid('n_dodecanol') * 184)
        .fluidOutputs(fluid('n_tetradecanol') * 141)
        .fluidOutputs(fluid('n_hexadecanol') * 91)
        .duration(166)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

FBR.recipeBuilder()
        .notConsumable(metaitem('catalystBedAlumina'))
        .fluidInputs(fluid('n_octanol') * 50)
        .fluidInputs(fluid('ammonia') * 50)
        .fluidOutputs(fluid('diluted_octylamine') * 100)
        .duration(3)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('diluted_octylamine') * 2000)
        .fluidOutputs(fluid('n_octylamine') * 1000)
        .fluidOutputs(fluid('water') * 1000)
        .duration(160)
        .EUt(120)
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(metaitem('dustCopperIiNitrate') * 2)
        .inputs(metaitem('dustNickelIiNitrate') * 2)
        .inputs(metaitem('dustZincNitrate') * 2)
        .inputs(metaitem('dustMagnesiumNitrate') * 2)
        .fluidInputs(fluid('water') * 1000)
        .fluidOutputs(fluid('metal_nitrate_solution') * 1000)
        .duration(300)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

AUTOCLAVE.recipeBuilder()
        .inputs(metaitem('dustSodaAsh') * 6)
        .fluidInputs(fluid('metal_nitrate_solution') * 1000)
        .fluidOutputs(fluid('neutralized_metal_nitrate_solution') * 1000)
        .duration(300)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

SINTERING_OVEN.recipeBuilder()
        .inputs(metaitem('dustZirconiumDioxide') * 3)
        .fluidInputs(fluid('neutralized_metal_nitrate_solution') * 1000)
        .outputs(metaitem('dustMetalNitrateCatalyst') * 4)
        .duration(300)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

LCR.recipeBuilder()
        .notConsumable(metaitem('dustMetalNitrateCatalyst'))
        .fluidInputs(fluid('nitrogen') * 1000)
        .fluidInputs(fluid('hydrogen') * 1000)
        .fluidInputs(fluid('n_octylamine') * 2000)
        .fluidOutputs(fluid('dioctylamine') * 1000)
        .duration(400)
        .EUt(480)
        .buildAndRegister()

BR.recipeBuilder()
        .inputs(metaitem('dustSodiumHydroxide') * 8)
        .fluidInputs(fluid('dioctylamine') * 2000)
        .inputs(metaitem('dustChloroaceticAnhydride') * 13)
        .fluidOutputs(fluid('diluted_tetraoctyl_diglycolamide') * 3000)
        .duration(400)
        .EUt(480)
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('diluted_tetraoctyl_diglycolamide') * 3000)
        .fluidOutputs(fluid('tetraoctyl_diglycolamide') * 1000)
        .fluidOutputs(fluid('salt_water') * 2000)
        .duration(200)
        .EUt(120)
        .buildAndRegister()

// D2EHPA
CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('sodium_hydroxide_solution') * 1000)
        .fluidInputs(fluid('butyraldehyde') * 2000)
        .fluidOutputs(fluid('two_ethyl_two_hexenal') * 3000)
        .fluidOutputs(fluid('diluted_sodium_hydroxide_solution') * 1000)
        .duration(3)
        .EUt(120)
        .buildAndRegister()

DISTILLERY.recipeBuilder()
        .fluidInputs(fluid('diluted_sodium_hydroxide_solution') * 2000)
        .fluidOutputs(fluid('water') * 2000)
        .outputs(metaitem('dustSodiumHydroxide') * 4)
        .duration(100)
        .EUt(120)
        .buildAndRegister()

FBR.recipeBuilder()
        .notConsumable(metaitem('catalystBedCobalt'))
        .fluidInputs(fluid('two_ethyl_two_hexenal') * 100)
        .fluidInputs(fluid('hydrogen') * 200)
        .fluidOutputs(fluid('two_ethylhexanol') * 50)
        .duration(5)
        .EUt(120)
        .buildAndRegister()

BR.recipeBuilder()
        .inputs(metaitem('dustPhosphorusPentoxide') * 7)
        .fluidInputs(fluid('two_ethylhexanol') * 6000)
        .fluidOutputs(fluid('two_ethylhexyl_phosphoric_acid_mix') * 2000)
        .duration(200)
        .EUt(120)
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('two_ethylhexyl_phosphoric_acid_mix') * 2000)
        .fluidInputs(fluid('hexane') * 1000)
        .fluidOutputs(fluid('mono_two_ethylhexyl_phosphoric_acid') * 1000)
        .fluidOutputs(fluid('di_two_ethylhexyl_phosphoric_acid_solution') * 2000)
        .duration(200)
        .EUt(120)
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('di_two_ethylhexyl_phosphoric_acid_solution') * 2000)
        .fluidOutputs(fluid('di_two_ethylhexyl_phosphoric_acid') * 1000)
        .fluidOutputs(fluid('hexane') * 1000)
        .duration(200)
        .EUt(120)
        .buildAndRegister()

//N1923
MIXER.recipeBuilder()
        .inputs(metaitem('dustYttriumOxide') * 5)
        .inputs(metaitem('dustNeodymiumOxide') * 5)
        .inputs(metaitem('dustPurifiedManganeseDioxide') * 3)
        .outputs(metaitem('dustRareEarthCatalystYNd') * 13)
        .duration(200)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()     

//C10 & 12 ACIDS
LCR.recipeBuilder()
        .inputs(metaitem('dustChromiumTrioxide') * 16)
        .fluidInputs(fluid('sulfuric_acid') * 6000)
        .fluidInputs(fluid('distilled_water') * 15000)
        .fluidInputs(fluid('acetone') * 1000)
        .fluidInputs(fluid('n_decanol') * 3000)
        .fluidOutputs(fluid('chromium_sulfate_solution') * 1000)
        .fluidOutputs(fluid('capric_acid') * 3000)
        .duration(300)
        .EUt(480)
        .buildAndRegister()

LCR.recipeBuilder()
        .inputs(metaitem('dustChromiumTrioxide') * 16)
        .fluidInputs(fluid('sulfuric_acid') * 6000)
        .fluidInputs(fluid('distilled_water') * 15000)
        .fluidInputs(fluid('acetone') * 1000)
        .fluidInputs(fluid('n_dodecanol') * 3000)
        .fluidOutputs(fluid('chromium_sulfate_solution') * 1000)
        .fluidOutputs(fluid('lauric_acid') * 3000)
        .duration(300)
        .EUt(480)
        .buildAndRegister()

LCR.recipeBuilder()
        .notConsumable(metaitem('dustRareEarthCatalystYNd'))
        .fluidInputs(fluid('lauric_acid') * 1000)
        .fluidInputs(fluid('capric_acid') * 1000)
        .fluidInputs(fluid('nitrogen') * 2000)
        .fluidOutputs(fluid('diluted_primary_amine_n') * 2000)
        .fluidOutputs(fluid('carbon_dioxide') * 3000)
        .duration(300)
        .EUt(480)
        .buildAndRegister()

VACUUM_DT.recipeBuilder()
        .fluidInputs(fluid('diluted_primary_amine_n') * 1000)
        .fluidOutputs(fluid('primary_amine_n') * 400)
        .duration(200)
        .EUt(120)
        .buildAndRegister()

//DBC
FBR.recipeBuilder()
        .notConsumable(metaitem('catalystBedPlatinum'))
        .fluidInputs(fluid('butyraldehyde') * 50)
        .fluidInputs(fluid('hydrogen') * 100)
        .fluidOutputs(fluid('n_butanol') * 50)
        .duration(5)
        .EUt(120)
        .buildAndRegister()

CSTR.recipeBuilder()
        .notConsumable(fluid('sulfuric_acid') * 50)
        .fluidInputs(fluid('diethylene_glycol') * 50)
        .fluidInputs(fluid('n_butanol') * 100)
        .fluidOutputs(fluid('dibutyl_carbitol') * 50)
        .duration(5)
        .EUt(120)
        .buildAndRegister()

//TRI OCTYL DECYL AMINE
CSTR.recipeBuilder()
        .fluidInputs(fluid('hydrobromic_acid') * 50)
        .fluidInputs(fluid('n_octanol') * 50)
        .fluidOutputs(fluid('diluted_bromooctane') * 100)
        .duration(5)
        .EUt(120)
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('diluted_bromooctane') * 2000)
        .fluidOutputs(fluid('bromooctane') * 1000)
        .fluidOutputs(fluid('water') * 1000)
        .duration(160)
        .EUt(120)
        .buildAndRegister()

BR.recipeBuilder()
        .fluidInputs(fluid('bromooctane') * 2000)
        .inputs(metaitem('dustLithium'))
        .inputs(metaitem('dustCopper'))
        .fluidOutputs(fluid('lithium_dioctylcopper') * 1000)
        .duration(160)
        .EUt(120)
        .buildAndRegister()

CSTR.recipeBuilder()
        .fluidInputs(fluid('capric_acid') * 150)
        .fluidInputs(fluid('phosphorus_trichloride') * 50)
        .fluidOutputs(fluid('decanoyl_chloride') * 150)
        .fluidOutputs(fluid('phosphoric_acid') * 50)
        .duration(10)
        .EUt(120)
        .buildAndRegister()

BR.recipeBuilder()
        .fluidInputs(fluid('capric_acid') * 1000)
        .fluidInputs(fluid('thionyl_chloride') * 1000)
        .fluidOutputs(fluid('decanoyl_chloride') * 1000)
        .fluidOutputs(fluid('hydrogen_chloride') * 1000)
        .fluidOutputs(fluid('sulfur_dioxide') * 1000)
        .duration(160)
        .EUt(120)
        .buildAndRegister()

CSTR.recipeBuilder()
        .fluidInputs(fluid('lithium_dioctylcopper') * 100)
        .fluidInputs(fluid('decanoyl_chloride') * 100)
        .fluidOutputs(fluid('nine_octadecanone') * 150)
        .duration(15)
        .EUt(120)
        .buildAndRegister()

BR.recipeBuilder()
        .fluidInputs(fluid('diborane') * 500)
        .inputs(metaitem('dustSodiumCyanide') * 6)
        .outputs(metaitem('dustSodiumCyanoborohydride') * 14)
        .duration(160)
        .EUt(120)
        .buildAndRegister()

LCR.recipeBuilder()
        .notConsumable(fluid('sulfuric_acid') * 1000)
        .notConsumable(metaitem('dustSodiumCyanoborohydride') * 7)
        .fluidInputs(fluid('nine_octadecanone') * 3000)
        .fluidInputs(fluid('ammonia') * 1000)
        .fluidOutputs(fluid('tri_octyl_decyl_amine') * 1000)
        .duration(300)
        .EUt(480)
        .buildAndRegister()

//ION EXCHANGE RESINS
//AG 50W-X8
POLYMERIZATION.recipeBuilder()
        .circuitMeta(1)
        .fluidInputs(fluid('polystyrene') * 1000)
        .fluidInputs(fluid('divinylbenzene') * 1000)
        .inputs(ore('dustPotassiumPersulfate'))
        .fluidOutputs(fluid('crosslinked_polystyrene') * 1008)
        .EUt(120)
        .duration(160)
        .buildAndRegister()

CSTR.recipeBuilder()
        .notConsumable(fluid('sulfuric_acid') * 50)
        .fluidInputs(fluid('ethylbenzene') * 50)
        .fluidInputs(fluid('ethylene') * 50)
        .fluidOutputs(fluid('diethylbenzene') * 50)
        .duration(5)
        .EUt(120)
        .buildAndRegister()

CHEMICAL_BATH.recipeBuilder()
        .inputs(metaitem('roundCrosslinkedPolystyrene') * 32)
        .fluidInputs(fluid('oleum') * 1000)
        .outputs(metaitem('ag_fifty_w_x_eight_beads'))
        .fluidOutputs(fluid('sulfuric_acid') * 1000)
        .EUt(120)
        .duration(200)
        .buildAndRegister()

FLUIDIZEDBR.recipeBuilder()
        .fluidInputs(fluid('diethylbenzene') * 1000)
        .notConsumable(ore('dustChromiumTrioxide'))
        .fluidOutputs(fluid('divinylbenzene') * 1000)
        .fluidOutputs(fluid('hydrogen') * 4000)
        .duration(300)
        .EUt(120)
        .buildAndRegister()

//AMBERLYST, MIBK, AND MIBC

POLYMERIZATION.recipeBuilder()
        .inputs(ore('dustPalladium') * 2)
        .fluidInputs(fluid('polystyrene') * 1000)
        .fluidInputs(fluid('divinylbenzene') * 1000)
        .inputs(ore('dustPotassiumPersulfate'))
        .fluidOutputs(fluid('palladium_doped_crosslinked_polystyrene') * 1008)
        .EUt(120)
        .duration(160)
        .buildAndRegister()

CSTR.recipeBuilder()
        .notConsumable(fluid('sulfuric_acid') * 50)
        .fluidInputs(fluid('ethylbenzene') * 50)
        .fluidInputs(fluid('ethylene') * 50)
        .fluidOutputs(fluid('diethylbenzene') * 50)
        .duration(5)
        .EUt(120)
        .buildAndRegister()

CSTR.recipeBuilder()
        .fluidInputs(fluid('sulfuric_acid') * 50)
        .fluidInputs(fluid('sulfur_trioxide') * 5)
        .fluidOutputs(fluid('oleum') * 50)
        .duration(5)
        .EUt(120)
        .buildAndRegister()

CHEMICAL_BATH.recipeBuilder()
        .inputs(metaitem('roundPalladiumDopedCrosslinkedPolystyrene') * 32)
        .fluidInputs(fluid('oleum') * 1000)
        .outputs(metaitem('amberlyst_ch_beads'))
        .fluidOutputs(fluid('sulfuric_acid') * 1000)
        .EUt(120)
        .duration(200)
        .buildAndRegister()

LCR.recipeBuilder()
        .notConsumable(metaitem('amberlyst_ch_beads'))
        .fluidInputs(fluid('acetone') * 2000)
        .fluidInputs(fluid('hot_hp_hydrogen') * 2000)
        .fluidOutputs(fluid('diluted_methyl_isobutyl_ketone') * 2000)
        .duration(300)
        .EUt(480)
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('diluted_methyl_isobutyl_ketone') * 2000)
        .fluidOutputs(fluid('water') * 1000)
        .fluidOutputs(fluid('methyl_isobutyl_ketone') * 1000)
        .duration(200)
        .EUt(120)
        .buildAndRegister()

FBR.recipeBuilder()
        .notConsumable(metaitem('catalystBedCopper'))
        .fluidInputs(fluid('methyl_isobutyl_ketone') * 50)
        .fluidInputs(fluid('hydrogen') * 100)
        .fluidOutputs(fluid('methyl_isobutyl_carbinol') * 50)
        .duration(5)
        .EUt(120)
        .buildAndRegister()