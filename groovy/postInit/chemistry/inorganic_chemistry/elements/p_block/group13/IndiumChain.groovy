import globals.Globals

BR = recipemap('batch_reactor')
BCR = recipemap('bubble_column_reactor')
ROASTER = recipemap('roaster')
CHEMICAL_BATH = recipemap('chemical_bath')
ELECTROLYZER = recipemap('electrolyzer')
MACERATOR = recipemap('macerator')
VACUUM_DT = recipemap('vacuum_distillation')
ELECTROLYTIC_CELL = recipemap('electrolytic_cell')

// From zinc calcine (zincite) or zinc oxide fume from lead

BR.recipeBuilder()
    .inputs(ore('dustZincLeachResidue') * 8)
    .fluidInputs(fluid('diluted_hydrochloric_acid') * 2000)
    .outputs(metaitem('dustZincHydrochloricLeachResidue') * 8)
    .fluidOutputs(fluid('indium_leach') * 2000)
    .EUt(30)
    .duration(160)
    .buildAndRegister()

BR.recipeBuilder()
    .inputs(ore('dustSodaAsh') * 3)
    .fluidInputs(fluid('indium_leach') * 2000)
    .chancedOutput(metaitem('dustIndiumResidue'), 8000, 0)
    .fluidOutputs(fluid('carbon_dioxide') * 500)
    .fluidOutputs(fluid('wastewater') * 2000)
    .EUt(30)
    .duration(160)
    .buildAndRegister()

BR.recipeBuilder()
    .inputs(ore('dustIndiumResidue'))
    .fluidInputs(fluid('sodium_hydroxide_solution') * 1000)
    .outputs(metaitem('dustCrudeIndiumIiiHydroxide') * 7)
    .fluidOutputs(fluid('wastewater') * 1000)
    .EUt(30)
    .duration(160)
    .buildAndRegister()
    
BR.recipeBuilder()
    .inputs(ore('dustCrudeIndiumIiiHydroxide') * 14)
    .fluidInputs(fluid('sulfuric_acid') * 3000)
    .fluidOutputs(fluid('crude_indium_iii_sulfate_solution') * 6000)
    .EUt(30)
    .duration(320)
    .buildAndRegister()

BCR.recipeBuilder()
    .fluidInputs(fluid('crude_indium_iii_sulfate_solution') * 300)
    .fluidInputs(fluid('hydrogen_sulfide') * 5)
    .fluidOutputs(fluid('indium_iii_sulfate_solution') * 300)
    .EUt(30)
    .duration(16)
    .buildAndRegister()

BR.recipeBuilder()
    .inputs(ore('dustZinc') * 3)
    .fluidInputs(fluid('indium_iii_sulfate_solution') * 6000)
    .outputs(metaitem('sponge.indium') * 2)
    .fluidOutputs(fluid('zinc_sulfate_solution') * 6000)
    .EUt(30)
    .duration(320)
    .buildAndRegister()

ELECTROLYZER.recipeBuilder()
    .notConsumable(metaitem('graphite_electrode'))
    .notConsumable(metaitem('stickZinc'))
    .fluidInputs(fluid('zinc_sulfate_solution') * 2000)
    .outputs(metaitem('dustZinc'))
    .fluidOutputs(fluid('diluted_sulfuric_acid') * 2000)
    .fluidOutputs(fluid('oxygen') * 1000)
    .EUt(30)
    .duration(320)
    .buildAndRegister()

// From lead electrorefining

ROASTER.recipeBuilder()
    .circuitMeta(2)
    .inputs(metaitem('anode_slime.lead'))
    .fluidInputs(fluid('sulfuric_acid') * 150)
    .outputs(metaitem('dustSulfatizedLeadSlime'))
    .EUt(Globals.voltAmps[3])
    .duration(200)
    .buildAndRegister()

CHEMICAL_BATH.recipeBuilder()
    .inputs(ore('dustSulfatizedLeadSlime'))
    .fluidInputs(fluid('distilled_water') * 300)
    .outputs(metaitem('dustBlackMetal'))
    .fluidOutputs(fluid('impure_indium_leach') * 300)
    .EUt(30)
    .duration(320)
    .buildAndRegister()

BR.recipeBuilder()
    .inputs(ore('dustIndium') * 2)
    .fluidInputs(fluid('impure_indium_leach') * 12000)
    .outputs(metaitem('dustCopper') * 3)
    .fluidOutputs(fluid('indium_iii_sulfate_solution') * 12000)
    .EUt(30)
    .duration(320)
    .buildAndRegister()

// Purification

MACERATOR.recipeBuilder()
    .inputs(metaitem('sponge.indium'))
    .outputs(metaitem('dustIndium'))
    .EUt(30)
    .duration(20)
    .buildAndRegister()

VACUUM_DT.recipeBuilder()
    .inputs(ore('dustIndium'))
    .chancedOutput(metaitem('dustHighPurityIndium'), 9900, 0)
    .EUt(120)
    .duration(1000)
    .buildAndRegister()

MIXER.recipeBuilder()
    .inputs(ore('dustIndiumChloride') * 4)
    .fluidInputs(fluid('distilled_water') * 1000)
    .fluidOutputs(fluid('indium_chloride_solution') * 1000)
    .EUt(30)
    .duration(20)
    .buildAndRegister()

ELECTROLYTIC_CELL.recipeBuilder()
    .notConsumable(ore('plateHighPurityIndium'))
    .notConsumable(fluid('indium_chloride_solution') * 1000)
    .inputs(ore('plateIndium'))
    .chancedOutput(metaitem('dustHighPurityIndium'), 9900, 0)
    .EUt(120)
    .duration(100)
    .buildAndRegister()