FLOTATION = recipemap('froth_flotation')
CLARIFIER = recipemap('clarifier')
CSTR = recipemap('continuous_stirred_tank_reactor')
TBR = recipemap('trickle_bed_reactor')
FBR = recipemap('fixed_bed_reactor')
BCR = recipemap('bubble_column_reactor')
BR = recipemap('batch_reactor')
POLYMERIZATION = recipemap('polymerization_tank')
FLUIDIZEDBR = recipemap('fluidized_bed_reactor')
HT_DISTILLATION_TOWER = recipemap('high_temperature_distillation')
DISTILLERY = recipemap('distillery')
ROASTER = recipemap('roaster')
MIXER = recipemap('mixer')
DRYER = recipemap('dryer')
SIFTER = recipemap('sifter')
CENTRIFUGE = recipemap('centrifuge')
PYROLYSE = recipemap('pyrolyse_oven')
LCR = recipemap('large_chemical_reactor')
EBF = recipemap('electric_blast_furnace')
VULCANIZER = recipemap('vulcanizing_press')
ALLOY_SMELTER = recipemap('alloy_smelter')
ARC_FURNACE = recipemap('arc_furnace')
AUTOCLAVE = recipemap('autoclave')
CHEMICAL_BATH = recipemap('chemical_bath')
ASSEMBLER = recipemap('assembler')
ELECTROLYTIC_CELL = recipemap('electrolytic_cell')
REACTION_FURNACE = recipemap('reaction_furnace')
ELECTROMAGNETIC_SEPARATOR = recipemap('electromagnetic_separator')
FLUID_HEATER = recipemap('fluid_heater')
FLUID_SOLIDIFIER = recipemap('fluid_solidifier')

// Zincite Dust * 1
mods.gregtech.electric_blast_furnace.removeByInput(120, [metaitem('dustSphalerite')], [fluid('oxygen') * 3000])

def COAL_SOURCES = [
        "dustCarbon",
        "gemCoal",
        "dustCoal",
        "gemCharcoal",
        "dustCoke",
        "gemCoke",
        "dustCharcoal",
        "gemAnthracite",
        "dustAnthracite"
]

MIXER.recipeBuilder()
        .inputs(metaitem('dustImpureSphalerite') * 4)
        .fluidInputs(fluid('water') * 2000)
        .fluidOutputs(fluid('impure_sphalerite_slurry') * 2000)
        .EUt(30)
        .duration(100)
        .buildAndRegister()

FLOTATION.recipeBuilder()
        .fluidInputs(fluid('impure_sphalerite_slurry') * 16000)
        .notConsumable(metaitem('dustSodiumEthylXanthate'))
        .notConsumable(fluid('cresol') * 100)
        .notConsumable(fluid('soda_ash_solution') * 1000)
        .fluidOutputs(fluid('galena_slurry') * 3000)
        .fluidOutputs(fluid('unprocessed_sphalerite_slurry') * 16000)
        .EUt(480)
        .duration(200)
        .buildAndRegister()

FLOTATION.recipeBuilder()
        .fluidInputs(fluid('unprocessed_sphalerite_slurry') * 16000)
        .notConsumable(metaitem('dustPotassiumAmylXanthate'))
        .notConsumable(fluid('soda_ash_solution') * 1000)
        .fluidOutputs(fluid('sphalerite_slurry') * 16000)
        .outputs(metaitem('dustGraniteTailings') * 16)
        .EUt(480)
        .duration(200)
        .buildAndRegister()

CLARIFIER.recipeBuilder()
        .fluidInputs(fluid('sphalerite_slurry') * 16000)
        .fluidOutputs(fluid('wastewater') * 16000)
        .outputs(metaitem('dustSphalerite') * 64)
        .EUt(30)
        .duration(200)
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(metaitem('dustImpureSmithsonite') * 4)
        .fluidInputs(fluid('water') * 2000)
        .fluidOutputs(fluid('impure_smithsonite_slurry') * 2000)
        .EUt(30)
        .duration(100)
        .buildAndRegister()

FLOTATION.recipeBuilder()
        .fluidInputs(fluid('impure_smithsonite_slurry') * 16000)
        .notConsumable(metaitem('dustSodiumEthylXanthate'))
        .notConsumable(fluid('cresol') * 100)
        .notConsumable(fluid('soda_ash_solution') * 1000)
        .fluidOutputs(fluid('smithsonite_slurry') * 16000)
        .EUt(480)
        .duration(200)
        .buildAndRegister()

CLARIFIER.recipeBuilder()
        .fluidInputs(fluid('smithsonite_slurry') * 16000)
        .fluidOutputs(fluid('wastewater') * 16000)
        .outputs(metaitem('dustSmithsonite') * 64)
        .EUt(30)
        .duration(200)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(metaitem('dustSphalerite') * 1)
        .fluidOutputs(fluid('sulfur_dioxide') * 1000)
        .outputs(metaitem('dustZincite') * 2)
        .EUt(30)
        .duration(200)
        .buildAndRegister()

FLUIDIZEDBR.recipeBuilder()
        .inputs(metaitem('dustSphalerite') * 1)
        .fluidInputs(fluid('oxygen') * 3000)
        .fluidOutputs(fluid('thallium_rich_flue_gas') * 1000)
        .outputs(metaitem('dustZincite') * 2)
        .outputs(metaitem('dustZincRichSlag') * 1)
        .EUt(120)
        .duration(20)
        .buildAndRegister()

SIFTER.recipeBuilder()
        .notConsumable(metaitem('item_filter'))
        .fluidInputs(fluid('thallium_rich_flue_gas') * 1000)
        .chancedOutput(metaitem('dustThalliumRichFlue'), 1000, 250)
        .fluidOutputs(fluid('sulfur_dioxide') * 1000)
        .EUt(120)
        .duration(20)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(metaitem('dustSmithsonite') * 1)
        .fluidOutputs(fluid('carbon_dioxide') * 1000)
        .outputs(metaitem('dustZincite') * 2)
        .EUt(30)
        .duration(200)
        .buildAndRegister()

FLUIDIZEDBR.recipeBuilder()
        .inputs(metaitem('dustSmithsonite') * 10)
        .fluidOutputs(fluid('carbon_dioxide') * 10000)
        .outputs(metaitem('dustZincite') * 25)
        .EUt(120)
        .duration(200)
        .buildAndRegister()

for (coal_source in COAL_SOURCES) {
    ROASTER.recipeBuilder()
            .inputs(metaitem('dustZincite') * 1)
            .inputs(ore(coal_source) * 1)
            .fluidOutputs(fluid('crude_zinc') * 216)
            .fluidOutputs(fluid('carbon_monoxide') * 1000)
            .EUt(30)
            .duration(160)
            .buildAndRegister()
}

FLUID_SOLIDIFIER.recipeBuilder()
        .notConsumable(metaitem('shape.mold.ingot'))
        .fluidInputs(fluid('crude_zinc') * 216)
        .outputs(metaitem('ingotZinc'))
        .EUt(7)
        .duration(20)
        .buildAndRegister()

FLUID_SOLIDIFIER.recipeBuilder()
        .notConsumable(metaitem('shape.mold.ingot'))
        .fluidInputs(fluid('cadmium_rich_zinc') * 180)
        .outputs(metaitem('ingotZinc'))
        .EUt(7)
        .duration(20)
        .buildAndRegister()

HT_DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('crude_zinc') * 2160)
        .chancedOutput(metaitem('dustIron'), 200, 100)
        .fluidOutputs(fluid('cadmium_rich_zinc') * 2160)
        .fluidOutputs(fluid('copper') * 36)
        .fluidOutputs(fluid('lead') * 72)
        .fluidOutputs(fluid('tin') * 36)
        .EUt(120)
        .duration(300)
        .buildAndRegister()

HT_DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('cadmium_rich_zinc') * 2160)
        .fluidOutputs(fluid('zinc') * 2160)
        .fluidOutputs(fluid('cadmium') * 144)
        .EUt(480)
        .duration(300)
        .buildAndRegister()

CHEMICAL_BATH.recipeBuilder()
        .inputs(metaitem('dustZincite') * 2)
        .fluidInputs(fluid('sulfuric_acid') * 1000)
        .fluidOutputs(fluid('zinc_leach') * 1000)
        .outputs(metaitem('dustZincLeachResidue'))
        .EUt(30)
        .duration(160)
        .buildAndRegister()

FLUID_HEATER.recipeBuilder()
        .fluidInputs(fluid('sulfuric_acid') * 1000)
        .fluidOutputs(fluid('hot_sulfuric_acid') * 1000)
        .EUt(30)
        .duration(100)
        .buildAndRegister()

CHEMICAL_BATH.recipeBuilder()
        .inputs(metaitem('dustZincLeachResidue'))
        .fluidInputs(fluid('hot_sulfuric_acid') * 100)
        .fluidOutputs(fluid('hot_zinc_leach') * 100)
        .chancedOutput(metaitem('dustHotZincLeachResidue'), 500, 500)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(metaitem('dustHotZincLeachResidue'))
        .fluidOutputs(fluid('sulfur_trioxide') * 1000)
        .outputs(metaitem('dustSoftenedLead'))
        .EUt(30)
        .duration(160)
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(metaitem('dustSphalerite') * 2)
        .fluidInputs(fluid('hot_zinc_leach') * 16000)
        .fluidOutputs(fluid('reduced_zinc_leach') * 16000)
        .EUt(30)
        .duration(400)
        .buildAndRegister()

AUTOCLAVE.recipeBuilder()
        .fluidInputs(fluid('oxygen') * 1000)
        .fluidInputs(fluid('reduced_zinc_leach') * 16000)
        .fluidOutputs(fluid('zinc_leach') * 16000)
        .outputs(metaitem('dustIronIiiOxide') * 5)
        .EUt(30)
        .duration(400)
        .buildAndRegister()

EBF.recipeBuilder()
        .inputs(metaitem('dustZincRichSlag') * 10)
        .inputs(metaitem('dustCarbon') * 2)
        .outputs(metaitem('dustWaelzOxide') * 7)
        .outputs(metaitem('dustGermaniumRichSlag') * 3)
        .blastFurnaceTemp(1400)
        .EUt(120)
        .duration(200)
        .buildAndRegister()

HT_DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('waelz_oxide') * 1440)
        .chancedOutput(metaitem('dustLead'), 500, 300)
        .fluidOutputs(fluid('cadmium_rich_zinc') * 1440)
        .fluidOutputs(fluid('cadmium') * 72)
        .EUt(120)
        .duration(300)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('distilled_water') * 1000)
        .inputs(metaitem('dustZinc') * 1)
        .fluidOutputs(fluid('zinc_cementation_slurry') * 1000)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('zinc_leach') * 1000)
        .fluidInputs(fluid('zinc_cementation_slurry') * 100)
        .fluidOutputs(fluid('precipitated_zinc_leach') * 1000)
        .outputs(metaitem('dustCopperCadmiumResidue') * 1)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('sulfuric_acid') * 1000)
        .fluidInputs(fluid('water') * 1000)
        .inputs(metaitem('dustCopperCadmiumResidue') * 20)
        .fluidOutputs(fluid('cadmium_sulfate_solution') * 1000)
        .outputs(metaitem('dustCopper') * 2)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

FLUID_HEATER.recipeBuilder()
        .fluidInputs(fluid('precipitated_zinc_leach') * 1000)
        .fluidOutputs(fluid('hot_precipitated_zinc_leach') * 1000)
        .EUt(30)
        .duration(100)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('hot_precipitated_zinc_leach') * 1000)
        .fluidInputs(fluid('zinc_cementation_slurry') * 40)
        .fluidOutputs(fluid('reprecipitated_zinc_leach') * 1000)
        .outputs(metaitem('dustCobaltResidue') * 1)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('sulfuric_acid') * 1000)
        .inputs(metaitem('dustCopperCadmiumResidue') * 20)
        .fluidOutputs(fluid('zinc_leach') * 1000)
        .outputs(metaitem('dustCobalt') * 1)
        .EUt(30)
        .duration(160)
        .buildAndRegister()

ELECTROLYTIC_CELL.recipeBuilder()
        .notConsumable(metaitem('plateLead') * 4)
        .notConsumable(metaitem('plateAluminium') * 4)
        .circuitMeta(1)
        .fluidInputs(fluid('sulfuric_acid') * 50)
        .fluidInputs(fluid('distilled_water') * 50)
        .fluidInputs(fluid('reprecipitated_zinc_leach') * 1000)
        .outputs(metaitem('dustZinc'))
        .chancedOutput(metaitem('dustZinc'), 2500, 500)
        .fluidOutputs(fluid('hydrogen') * 100)
        .fluidOutputs(fluid('oxygen') * 1050)
        .fluidOutputs(fluid('sulfuric_acid') * 1050)
        .EUt(120)
        .duration(200)
        .buildAndRegister()

ELECTROLYTIC_CELL.recipeBuilder()
        .notConsumable(metaitem('plateLead') * 4)
        .notConsumable(metaitem('plateAluminium') * 4)
        .circuitMeta(2)
        .fluidInputs(fluid('sulfuric_acid') * 50)
        .fluidInputs(fluid('distilled_water') * 50)
        .fluidInputs(fluid('reprecipitated_zinc_leach') * 1000)
        .outputs(metaitem('dustHighPurityZinc'))
        .fluidOutputs(fluid('hydrogen') * 100)
        .fluidOutputs(fluid('oxygen') * 1050)
        .fluidOutputs(fluid('sulfuric_acid') * 1050)
        .EUt(120)
        .duration(200)
        .buildAndRegister()