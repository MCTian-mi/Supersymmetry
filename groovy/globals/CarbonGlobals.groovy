package globals

class CarbonGlobals {

    public static void main (String[] args) {}

    // Anthracite coal value, so most recipes would require
    // equal amount of carbon/coke/anthracite and 2x coal
    static final int UNIVERSAL_COAL_EQUIVALENT = 90

    // trait Combustible {
    //     int duration
    // }

    // trait HighPurityCombustible extends Combustible {}

    // trait Pyrolyzable {
    //     String pyrolysis_product
    // }

    // Get number of items which would contatin %required% amount of
    // material if single item has %provider% amount
    // E.g. for 90 carbon this would yield 1 for dustCarbon or
    // dustAnthracite and 2 for dustCharcoal or gemLigniteCoke
    static int num_item_by_provider(int required, int provider) {
        int result = required.intdiv(provider)
        if (required % provider > 0) {
            result += 1
        }
        return result
    }

    public static class CarbonSource {
        String name
        int carbon
        String byproduct
        boolean isCombustible = false
        boolean isHighPurityCombustible = false
        boolean isPyrolyzable = false
        int duration = 0
        String pyrolysis_product = null

        public int num_items_by_carbon(int required_carbon) {
            return num_item_by_provider(required_carbon, this.carbon)
        }

        // Return the number of CarbonSource items with summary carbon content
        // equal to carbon content of %required_carbon_items% anthracite items
        public int equivalent(int required_carbon_items) {
            return num_item_by_provider(required_carbon_items * UNIVERSAL_COAL_EQUIVALENT, this.carbon)
        }
    }

    public static sources = [
            new CarbonSource(name: 'dustHighPurityCarbon', carbon: 100, byproduct: 'dustTinyAsh', isHighPurityCombustible: true, duration: 1),
            new CarbonSource(name: 'dustCarbon', carbon: 100, byproduct: 'dustTinyAsh', isHighPurityCombustible: true, duration: 1),
            new CarbonSource(name: 'gemCoke', carbon: 100, byproduct: 'dustTinyAsh', isCombustible: true, isPyrolyzable: true, duration: 2, pyrolysis_product: 'dustCarbon'),
            new CarbonSource(name: 'dustCoke', carbon: 100, byproduct: 'dustTinyAsh', isHighPurityCombustible: true, isPyrolyzable: true, duration: 2, pyrolysis_product: 'dustCarbon'),
            new CarbonSource(name: 'gemAnthracite', carbon: 90, byproduct: 'dustTinyAsh', isCombustible: true, isPyrolyzable: true, duration: 2, pyrolysis_product: 'gemCoke'),
            new CarbonSource(name: 'dustAnthracite', carbon: 90, byproduct: 'dustTinyAsh', isCombustible: true, isPyrolyzable: true, duration: 2, pyrolysis_product: 'dustCoke'),
            new CarbonSource(name: 'gemLigniteCoke', carbon: 75, byproduct: 'dustTinyAsh', isCombustible: true, isPyrolyzable: true, duration: 3, pyrolysis_product: 'dustCarbon'),
            new CarbonSource(name: 'dustLigniteCoke', carbon: 75, byproduct: 'dustTinyAsh', isCombustible: true, isPyrolyzable: true, duration: 3, pyrolysis_product: 'dustCarbon'),
            new CarbonSource(name: 'gemCoal', carbon: 75, byproduct: 'dustTinyDarkAsh', isCombustible: true, isPyrolyzable: true, duration: 4, pyrolysis_product: 'gemCoke'),
            new CarbonSource(name: 'dustCoal', carbon: 75, byproduct: 'dustTinyDarkAsh', isCombustible: true, isPyrolyzable: true, duration: 4, pyrolysis_product: 'dustCoke'),
            new CarbonSource(name: 'gemCharcoal', carbon: 60, byproduct: 'dustTinyDarkAsh', isCombustible: true, isPyrolyzable: true, duration: 4, pyrolysis_product: 'gemCoke'),
            new CarbonSource(name: 'dustCharcoal', carbon: 60, byproduct: 'dustTinyDarkAsh', isCombustible: true, isPyrolyzable: true, duration: 4, pyrolysis_product: 'dustCoke'),
            new CarbonSource(name: 'gemLignite', carbon: 25, byproduct: 'dustTinyAsh', isPyrolyzable: true, pyrolysis_product: 'gemLigniteCoke'),
            new CarbonSource(name: 'dustLignite', carbon: 25, byproduct: 'dustTinyAsh', isPyrolyzable: true, pyrolysis_product: 'dustLigniteCoke')
    ]

    static Map sourcesMap = sources.collectEntries{[it.name, it]}
    public static def byName(String name) {sourcesMap[name] }
    public static List byNames(List names) { sourcesMap.subMap(names)*.value }

    public static List combustibles() { sources.findAll { it.isCombustible } }
    public static List highPurityCombustibles() { sources.findAll { it.isHighPurityCombustible } }
    public static List dusts() { sources.findAll { it.name.startsWith('dust') } }

}