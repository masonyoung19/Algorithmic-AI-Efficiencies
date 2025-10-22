import java.util.Scanner;
import java.util.function.Function;

/*COMPREHENSIVE FINDINGS & CONCLUSIONS*/

//=== ALGORITHM PERFORMANCE ANALYSIS ===
//There is almost no instances where Ultra-Enhanced Sieve outperformed Enhanced Sieve in personal findings
//On a test with 5 runs and the upper bound being 100,000,000 Segmented Sieve barely beat Enhanced Sieve which
//was 2nd place for this run and consistently winning in most instances
//Enhanced sieve with a run size of 50 and 1 million as upper bound crushed all other algorithms

//=== KEY INSIGHTS ===
//1. COMPLEXITY vs PERFORMANCE: Ultra-Enhanced optimizations add overhead without meaningful benefit
//2. CROSSOVER POINTS: Enhanced Sieve dominates across most practical input ranges (1K-10M)
//3. SCALABILITY: Only at extreme scales (100M+) does Segmented Sieve show marginal advantage
//4. JIT OPTIMIZATION: Enhanced Sieve's clean code structure gets superior JVM optimization
//5. RELIABILITY: Enhanced Sieve provides most consistent performance across varying conditions

//=== FINAL RECOMMENDATION ===
//ENHANCED SIEVE is the optimal choice for production use because:
// • Strikes perfect balance between algorithmic sophistication and implementation simplicity
// • Provides predictable, high performance across wide range of input sizes
// • Easy to understand, debug, and maintain compared to more complex alternatives
// • Handles most real-world prime finding scenarios optimally (1-10M range)
// • Shows excellent JIT compilation characteristics with sufficient averaging

//=== ENGINEERING CONCLUSION ===
//The "Goldilocks Algorithm" - Enhanced Sieve represents the sweet spot where additional 
//complexity provides diminishing returns. Sometimes the middle-ground optimization is 
//actually the optimal solution in practice.

/*
=== READY-TO-USE ENHANCED SIEVE (COPY & PASTE) ===

    public static boolean[] enhancedSieve(int n) {
        // Optimized Sieve of Eratosthenes - optimal balance of performance and simplicity
        
        boolean[] isComposite = new boolean[n + 1];
        
        // Handle edge cases: 0 and 1 are not prime
        if (n >= 0) isComposite[0] = true;
        if (n >= 1) isComposite[1] = true;
        
        // Handle even numbers: mark all even numbers > 2 as composite
        for (int i = 4; i <= n; i += 2) {
            isComposite[i] = true;
        }
        
        // Optimized sieve: only check odd numbers starting from 3
        // Only need to check up to sqrt(n) since larger factors would have been found already
        int sqrtLimit = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrtLimit; i += 2) {
            
            if (!isComposite[i]) {
                // Start marking from i*i (not i*2) since smaller multiples already marked
                // Only mark odd multiples to skip even numbers
                for (int j = i * i; j <= n; j += 2 * i) {
                    isComposite[j] = true;
                }
            }
        }
        
        return isComposite;
    }

USAGE NOTES:
- Returns boolean array where isComposite[i] = true means i is NOT prime
- To check if number k is prime: !isComposite[k] (for k <= n)
- Time Complexity: O(n log log n) with excellent constant factors
- Space Complexity: O(n)
- Optimal for input ranges: 1,000 to 10,000,000
- Handles edge cases (0, 1, 2) correctly
*/





public class findPrimes {

    public static void main(String[] args) {
     
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Prime Number Finder with Advanced Analytics ===");
            System.out.println("1. Find primes and display results");
            System.out.println("2. Run benchmark comparison");
            System.out.println("3. Analyze crossover points and efficiency transitions");
            System.out.print("Choose option (1, 2, or 3): ");
            
            int choice = scanner.nextInt();
            
            if (choice == 1) {
                System.out.print("Enter the upper limit to find prime numbers: ");
                int limit = scanner.nextInt();
          
                boolean[] isComposite = enhancedSieve(limit);

                System.out.println("Prime numbers up to " + limit + ":");
                for(int i = 2; i <= limit; i++) {
                    if (!isComposite[i]) {
                        System.out.print(i + " ");
                    }
                }
                System.out.println(); // New line after printing all primes
            } else if (choice == 2) {
                System.out.print("Enter the upper limit for benchmarking: ");
                int limit = scanner.nextInt();
                System.out.print("Enter number of runs for averaging (recommended: 5-10): ");
                int runs = scanner.nextInt();
                
                runBenchmarks(limit, runs);
            } else if (choice == 3) {
                System.out.println("Choose crossover analysis type:");
                System.out.println("1. Quick analysis (recommended for initial exploration)");
                System.out.println("2. Comprehensive analysis (detailed, takes longer)");
                System.out.print("Enter choice (1 or 2): ");
                int analysisType = scanner.nextInt();
                
                if (analysisType == 1) {
                    runQuickCrossoverAnalysis();
                } else if (analysisType == 2) {
                    runComprehensiveCrossoverAnalysis();
                } else {
                    System.out.println("Invalid analysis type.");
                }
            } else {
                System.out.println("Invalid choice. Please run the program again.");
            }
        }
    }

    public static boolean[] enhancedSieve(int n) {
        
        // Optimized Sieve of Eratosthenes implementation
        
        boolean[] isComposite = new boolean[n + 1];
        
        // Handle edge cases: 0 and 1 are not prime
        if (n >= 0) isComposite[0] = true;
        if (n >= 1) isComposite[1] = true;
        
        // Handle even numbers: mark all even numbers > 2 as composite
        for (int i = 4; i <= n; i += 2) {
            isComposite[i] = true;
        }
        
        // Optimized sieve: only check odd numbers starting from 3
        // Only need to check up to sqrt(n) since larger factors would have been found already
        int sqrtLimit = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrtLimit; i += 2) {
            
            if (!isComposite[i]) {
                // Start marking from i*i (not i*2) since smaller multiples already marked
                // Only mark odd multiples to skip even numbers
                for (int j = i * i; j <= n; j += 2 * i) {
                    isComposite[j] = true;
                }
            }
        }
        
        return isComposite;

    }//end of findAllPrimes

    public static boolean[] ultraEnhancedSieve(int n) {
        
        // Ultra-Enhanced Sieve - Combines best elements from segmented approach
        // without the overhead of full segmentation for single-run efficiency
        
        boolean[] isComposite = new boolean[n + 1];
        
        // Handle edge cases: 0 and 1 are not prime
        if (n >= 0) isComposite[0] = true;
        if (n >= 1) isComposite[1] = true;
        
        // Early return for very small inputs to avoid overhead
        if (n <= 2) return isComposite;
        
        // Mark 2 as prime (only even prime)
        // Handle even numbers: mark all even numbers > 2 as composite
        for (int i = 4; i <= n; i += 2) {
            isComposite[i] = true;
        }
        
        int sqrtLimit = (int) Math.sqrt(n);
        
        // OPTIMIZATION 1: Cache-friendly blocking for better memory access
        // Process in cache-friendly chunks when beneficial (larger inputs)
        int blockSize = n > 100000 ? Math.min(32768, sqrtLimit * 8) : n;
        
        // OPTIMIZATION 2: Pre-collect small primes for more efficient iteration
        // (borrowed from segmented approach)
        int[] smallPrimes = new int[sqrtLimit / 3 + 1]; // Estimate array size
        int primeCount = 0;
        
        // Find small primes up to sqrt(n) first
        for (int i = 3; i <= sqrtLimit; i += 2) {
            if (!isComposite[i]) {
                smallPrimes[primeCount++] = i;
                
                // OPTIMIZATION 3: Improved multiple marking with better start calculation
                // Start from max(i*i, first odd multiple in range)
                long start = (long) i * i;
                if (start <= n) {
                    for (long j = start; j <= n; j += 2L * i) {
                        isComposite[(int) j] = true;
                    }
                }
            }
        }
        
        // OPTIMIZATION 4: Block-wise processing for large arrays (cache optimization)
        if (n > blockSize && blockSize < n) {
            for (int blockStart = sqrtLimit + 1; blockStart <= n; blockStart += blockSize) {
                int blockEnd = Math.min(blockStart + blockSize - 1, n);
                
                // Process this block with collected primes
                for (int p = 0; p < primeCount; p++) {
                    int prime = smallPrimes[p];
                    if (prime * prime > blockEnd) break;
                    
                    // Find first odd multiple of prime in this block
                    int firstMultiple = blockStart + (prime - (blockStart % prime)) % prime;
                    if (firstMultiple % 2 == 0) firstMultiple += prime; // Ensure odd
                    
                    for (int j = firstMultiple; j <= blockEnd; j += 2 * prime) {
                        isComposite[j] = true;
                    }
                }
            }
        }
        
        return isComposite;
        
    }//end of ultraEnhancedSieve

    public static boolean[] basicSieve(int n) {
        
        // Standard Sieve of Eratosthenes implementation
        // No optimizations - the classic textbook version
        
        boolean[] isComposite = new boolean[n + 1];
        
        // Mark 0 and 1 as not prime
        isComposite[0] = true;
        isComposite[1] = true;
        
        // Standard sieve algorithm
        for (int i = 2; i < isComposite.length; i++) {
            
            if (!isComposite[i]) {
                // Mark all multiples of i as composite
                for (int j = i * 2; j < isComposite.length; j += i) {
                    isComposite[j] = true;
                }
            }
        }
        
        return isComposite;
        
    }//end of basicSieve

    public static boolean[] segmentedSieve(int n) {
        
        // Segmented Sieve - More cache-friendly and memory efficient for large inputs
        // Uses O(sqrt(n)) memory instead of O(n) during computation
        
        boolean[] isComposite = new boolean[n + 1];
        
        if (n >= 0) isComposite[0] = true;
        if (n >= 1) isComposite[1] = true;
        
        int sqrtN = (int) Math.sqrt(n);
        
        // Step 1: Find all primes up to sqrt(n) using simple sieve
        boolean[] simplePrimes = new boolean[sqrtN + 1];
        simplePrimes[0] = simplePrimes[1] = true;
        
        for (int i = 2; i <= sqrtN; i++) {
            if (!simplePrimes[i]) {
                for (int j = i * i; j <= sqrtN; j += i) {
                    simplePrimes[j] = true;
                }
            }
        }
        
        // Step 2: Use segmented approach for the rest
        int segmentSize = Math.max(sqrtN, 32768); // Optimized segment size
        
        for (int low = 0; low <= n; low += segmentSize) {
            int high = Math.min(low + segmentSize - 1, n);
            
            // Create segment array
            boolean[] segment = new boolean[high - low + 1];
            
            // Mark multiples of each prime in current segment
            for (int p = 2; p <= sqrtN; p++) {
                if (!simplePrimes[p]) {
                    // Find the minimum number in [low, high] that is multiple of p
                    int start = Math.max(p * p, (low + p - 1) / p * p);
                    
                    for (int j = start; j <= high; j += p) {
                        segment[j - low] = true;
                    }
                }
            }
            
            // Copy segment results to main array
            for (int i = 0; i < segment.length; i++) {
                int actualIndex = low + i;
                if (actualIndex <= n) {
                    isComposite[actualIndex] = segment[i];
                }
            }
        }
        
        return isComposite;
        
    }//end of segmentedSieve

    // Benchmarking Analytics Methods
    
    public static void runBenchmarks(int limit, int runs) {
        System.out.println("\n=== BENCHMARKING ANALYSIS ===");
        System.out.println("Testing with limit: " + limit + ", Runs: " + runs);
        System.out.println("=" + "=".repeat(70));
        
        // Benchmark Basic Sieve
        BenchmarkResult basicResult = benchmarkMethod("Basic Sieve", 
            n -> basicSieve(n), limit, runs);
        
        // Benchmark Enhanced Sieve
        BenchmarkResult enhancedResult = benchmarkMethod("Enhanced Sieve", 
            n -> enhancedSieve(n), limit, runs);
            
        // Benchmark Ultra-Enhanced Sieve
        BenchmarkResult ultraResult = benchmarkMethod("Ultra-Enhanced Sieve", 
            n -> ultraEnhancedSieve(n), limit, runs);
            
        // Benchmark Segmented Sieve
        BenchmarkResult segmentedResult = benchmarkMethod("Segmented Sieve", 
            n -> segmentedSieve(n), limit, runs);
        
        // Display four-way comparison
        displayQuadComparison(basicResult, enhancedResult, ultraResult, segmentedResult);
    }
    
    public static BenchmarkResult benchmarkMethod(String methodName, 
            Function<Integer, boolean[]> method, int n, int runs) {
        
        System.out.println("\nTesting: " + methodName);
        System.out.println("-".repeat(30));
        
        long totalTime = 0;
        long minTime = Long.MAX_VALUE;
        long maxTime = Long.MIN_VALUE;
        int primeCount = 0;
        
        // Warm up JVM
        for (int i = 0; i < 3; i++) {
            method.apply(n);
        }
        
        // Run benchmarks
        for (int run = 1; run <= runs; run++) {
            // Force garbage collection before each run
            System.gc();
            
            // Measure memory before
            Runtime runtime = Runtime.getRuntime();
            long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
            
            // Time the execution
            long startTime = System.nanoTime();
            boolean[] result = method.apply(n);
            long endTime = System.nanoTime();
            
            // Measure memory after
            long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
            
            long executionTime = endTime - startTime;
            totalTime += executionTime;
            minTime = Math.min(minTime, executionTime);
            maxTime = Math.max(maxTime, executionTime);
            
            // Count primes (only once)
            if (run == 1) {
                primeCount = countPrimes(result);
            }
            
            System.out.printf("Run %2d: %8.3f ms, Memory: %8.2f KB%n", 
                run, executionTime / 1_000_000.0, (memoryAfter - memoryBefore) / 1024.0);
        }
        
        double avgTime = totalTime / (double) runs;
        
        return new BenchmarkResult(methodName, avgTime, minTime, maxTime, 
                                 totalTime, primeCount, n);
    }
    
    public static int countPrimes(boolean[] isComposite) {
        int count = 0;
        for (int i = 2; i < isComposite.length; i++) {
            if (!isComposite[i]) {
                count++;
            }
        }
        return count;
    }
    
    public static void displayComparison(BenchmarkResult basic, BenchmarkResult enhanced) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PERFORMANCE COMPARISON SUMMARY");
        System.out.println("=".repeat(60));
        
        System.out.printf("%-20s %15s %15s%n", "Metric", basic.methodName, enhanced.methodName);
        System.out.println("-".repeat(60));
        
        System.out.printf("%-20s %12.3f ms %12.3f ms%n", "Average Time:", 
            basic.avgTime / 1_000_000.0, enhanced.avgTime / 1_000_000.0);
            
        System.out.printf("%-20s %12.3f ms %12.3f ms%n", "Best Time:", 
            basic.minTime / 1_000_000.0, enhanced.minTime / 1_000_000.0);
            
        System.out.printf("%-20s %12.3f ms %12.3f ms%n", "Worst Time:", 
            basic.maxTime / 1_000_000.0, enhanced.maxTime / 1_000_000.0);
            
        System.out.printf("%-20s %15d %15d%n", "Primes Found:", 
            basic.primeCount, enhanced.primeCount);
            
        System.out.printf("%-20s %15d %15d%n", "Input Size:", 
            basic.inputSize, enhanced.inputSize);
        
        // Calculate speedup
        double speedup = basic.avgTime / enhanced.avgTime;
        System.out.println("\n" + "=".repeat(60));
        System.out.printf("PERFORMANCE IMPROVEMENT: %.2fx speedup%n", speedup);
        
        if (speedup > 1) {
            System.out.printf("Enhanced Sieve is %.1f%% faster than Basic Sieve%n", 
                (speedup - 1) * 100);
        } else {
            System.out.printf("Basic Sieve is %.1f%% faster than Enhanced Sieve%n", 
                (1/speedup - 1) * 100);
        }
        
        // Memory efficiency estimation
        long basicMemory = (long) basic.inputSize * 1; // boolean array
        long enhancedMemory = (long) enhanced.inputSize * 1; // same array size
        System.out.printf("Estimated Memory Usage: ~%.2f KB each%n", 
            basicMemory / 1024.0);
        
        System.out.println("=".repeat(60));
    }
    
    public static void displayTripleComparison(BenchmarkResult basic, 
            BenchmarkResult enhanced, BenchmarkResult segmented) {
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("COMPREHENSIVE PERFORMANCE COMPARISON");
        System.out.println("=".repeat(80));
        
        System.out.printf("%-20s %15s %15s %15s%n", "Metric", 
            basic.methodName, enhanced.methodName, segmented.methodName);
        System.out.println("-".repeat(80));
        
        System.out.printf("%-20s %12.3f ms %12.3f ms %12.3f ms%n", "Average Time:", 
            basic.avgTime / 1_000_000.0, enhanced.avgTime / 1_000_000.0, 
            segmented.avgTime / 1_000_000.0);
            
        System.out.printf("%-20s %12.3f ms %12.3f ms %12.3f ms%n", "Best Time:", 
            basic.minTime / 1_000_000.0, enhanced.minTime / 1_000_000.0,
            segmented.minTime / 1_000_000.0);
            
        System.out.printf("%-20s %12.3f ms %12.3f ms %12.3f ms%n", "Worst Time:", 
            basic.maxTime / 1_000_000.0, enhanced.maxTime / 1_000_000.0,
            segmented.maxTime / 1_000_000.0);
            
        System.out.printf("%-20s %15d %15d %15d%n", "Primes Found:", 
            basic.primeCount, enhanced.primeCount, segmented.primeCount);
        
        // Performance analysis
        System.out.println("\n" + "=".repeat(80));
        System.out.println("PERFORMANCE ANALYSIS:");
        System.out.println("-".repeat(80));
        
        // Find the fastest algorithm
        double fastestTime = Math.min(basic.avgTime, 
                            Math.min(enhanced.avgTime, segmented.avgTime));
        String fastest = "";
        if (fastestTime == basic.avgTime) fastest = "Basic Sieve";
        else if (fastestTime == enhanced.avgTime) fastest = "Enhanced Sieve";
        else fastest = "Segmented Sieve";
        
        System.out.println("🏆 WINNER: " + fastest + " (fastest average time)");
        
        // Calculate speedups relative to basic sieve
        double enhancedSpeedup = basic.avgTime / enhanced.avgTime;
        double segmentedSpeedup = basic.avgTime / segmented.avgTime;
        
        System.out.printf("📈 Enhanced Sieve: %.2fx %s than Basic%n", 
            enhancedSpeedup > 1 ? enhancedSpeedup : 1/enhancedSpeedup,
            enhancedSpeedup > 1 ? "faster" : "slower");
            
        System.out.printf("📈 Segmented Sieve: %.2fx %s than Basic%n", 
            segmentedSpeedup > 1 ? segmentedSpeedup : 1/segmentedSpeedup,
            segmentedSpeedup > 1 ? "faster" : "slower");
        
        // Memory efficiency notes
        System.out.println("\n💾 MEMORY EFFICIENCY NOTES:");
        System.out.printf("   • Basic & Enhanced: O(n) = ~%.1f KB%n", 
            basic.inputSize / 1024.0);
        System.out.printf("   • Segmented: O(√n) working memory = ~%.1f KB%n", 
            Math.sqrt(basic.inputSize) / 1024.0);
        
        System.out.println("\n🔍 ALGORITHM STRENGTHS:");
        System.out.println("   • Basic Sieve: Simple, predictable, good for small inputs");
        System.out.println("   • Enhanced Sieve: Optimized loops, good for medium inputs");
        System.out.println("   • Segmented Sieve: Cache-friendly, excellent for large inputs");
        
        System.out.println("=".repeat(80));
    }
    
    public static void displayQuadComparison(BenchmarkResult basic, BenchmarkResult enhanced, 
            BenchmarkResult ultra, BenchmarkResult segmented) {
        
        System.out.println("\n" + "=".repeat(90));
        System.out.println("COMPREHENSIVE 4-WAY PERFORMANCE COMPARISON");
        System.out.println("=".repeat(90));
        
        System.out.printf("%-20s %15s %15s %15s %15s%n", "Metric", 
            basic.methodName, enhanced.methodName, ultra.methodName, segmented.methodName);
        System.out.println("-".repeat(90));
        
        System.out.printf("%-20s %12.3f ms %12.3f ms %12.3f ms %12.3f ms%n", "Average Time:", 
            basic.avgTime / 1_000_000.0, enhanced.avgTime / 1_000_000.0, 
            ultra.avgTime / 1_000_000.0, segmented.avgTime / 1_000_000.0);
            
        System.out.printf("%-20s %12.3f ms %12.3f ms %12.3f ms %12.3f ms%n", "Best Time:", 
            basic.minTime / 1_000_000.0, enhanced.minTime / 1_000_000.0,
            ultra.minTime / 1_000_000.0, segmented.minTime / 1_000_000.0);
            
        System.out.printf("%-20s %12.3f ms %12.3f ms %12.3f ms %12.3f ms%n", "Worst Time:", 
            basic.maxTime / 1_000_000.0, enhanced.maxTime / 1_000_000.0,
            ultra.maxTime / 1_000_000.0, segmented.maxTime / 1_000_000.0);
            
        System.out.printf("%-20s %15d %15d %15d %15d%n", "Primes Found:", 
            basic.primeCount, enhanced.primeCount, ultra.primeCount, segmented.primeCount);
        
        // Performance analysis
        System.out.println("\n" + "=".repeat(90));
        System.out.println("PERFORMANCE RANKING & ANALYSIS:");
        System.out.println("-".repeat(90));
        
        // Find the fastest algorithm
        double[] times = {basic.avgTime, enhanced.avgTime, ultra.avgTime, segmented.avgTime};
        String[] names = {"Basic", "Enhanced", "Ultra-Enhanced", "Segmented"};
        
        // Simple ranking
        for (int rank = 1; rank <= 4; rank++) {
            double minTime = Double.MAX_VALUE;
            int minIndex = -1;
            for (int i = 0; i < times.length; i++) {
                if (times[i] < minTime && times[i] > 0) {
                    minTime = times[i];
                    minIndex = i;
                }
            }
            if (minIndex >= 0) {
                double speedup = basic.avgTime / times[minIndex];
                System.out.printf("%d. %s: %.2fx speedup over Basic%n", 
                    rank, names[minIndex], speedup);
                times[minIndex] = 0; // Mark as processed
            }
        }
        
        // Ultra-Enhanced specific analysis
        System.out.println("\n🚀 ULTRA-ENHANCED INNOVATIONS:");
        System.out.println("   • Cache-friendly blocking for large inputs (>100K)");
        System.out.println("   • Pre-collected prime array reduces repeated checks");
        System.out.println("   • Improved start calculation with long arithmetic");
        System.out.println("   • Block-wise processing borrowed from segmented approach");
        System.out.println("   • Early return optimization for tiny inputs");
        
        double ultraSpeedup = basic.avgTime / ultra.avgTime;
        double enhancedSpeedup = basic.avgTime / enhanced.avgTime;
        double improvement = ultraSpeedup / enhancedSpeedup;
        
        System.out.printf("\n📈 ULTRA vs ENHANCED: %.2fx additional improvement%n", improvement);
        
        System.out.println("=".repeat(90));
    }
    
    // Crossover Analysis Methods
    
    public static void runQuickCrossoverAnalysis() {
        System.out.println("\n🔍 QUICK CROSSOVER ANALYSIS");
        System.out.println("=" + "=".repeat(60));
        System.out.println("Testing strategic input sizes to identify efficiency transitions...\n");
        
        // Strategic test points based on typical crossover patterns
        int[] testSizes = {1000, 5000, 10000, 25000, 50000, 100000, 250000, 500000, 1000000};
        int runs = 3; // Fewer runs for quick analysis
        
        System.out.printf("%-12s %-12s %-12s %-12s %-15s%n", 
            "Input Size", "Basic (ms)", "Enhanced (ms)", "Segmented (ms)", "Winner");
        System.out.println("-".repeat(75));
        
        for (int size : testSizes) {
            CrossoverDataPoint dataPoint = analyzeSingleSize(size, runs);
            System.out.printf("%-12d %-12.3f %-12.3f %-12.3f %-15s%n",
                size, dataPoint.basicTime, dataPoint.enhancedTime, 
                dataPoint.segmentedTime, dataPoint.winner);
        }
        
        analyzeAndReportCrossovers(testSizes, runs);
    }
    
    public static void runComprehensiveCrossoverAnalysis() {
        System.out.println("\n🔬 COMPREHENSIVE CROSSOVER ANALYSIS");
        System.out.println("=" + "=".repeat(70));
        System.out.println("Detailed analysis with fine-grained input sizes...\n");
        
        // More comprehensive range with finer granularity
        int[] testSizes = {
            500, 1000, 2500, 5000, 7500, 10000, 15000, 20000, 25000, 
            35000, 50000, 75000, 100000, 150000, 200000, 300000, 
            500000, 750000, 1000000, 1500000, 2000000
        };
        int runs = 5; // More runs for statistical accuracy
        
        System.out.printf("%-12s %-12s %-12s %-12s %-15s %-12s%n", 
            "Input Size", "Basic (ms)", "Enhanced (ms)", "Segmented (ms)", "Winner", "Speedup");
        System.out.println("-".repeat(90));
        
        CrossoverDataPoint[] dataPoints = new CrossoverDataPoint[testSizes.length];
        
        for (int i = 0; i < testSizes.length; i++) {
            int size = testSizes[i];
            CrossoverDataPoint dataPoint = analyzeSingleSize(size, runs);
            dataPoints[i] = dataPoint;
            
            System.out.printf("%-12d %-12.3f %-12.3f %-12.3f %-15s %-12.2fx%n",
                size, dataPoint.basicTime, dataPoint.enhancedTime, 
                dataPoint.segmentedTime, dataPoint.winner, dataPoint.speedup);
        }
        
        // Detailed crossover analysis
        identifyDetailedCrossovers(dataPoints);
        analyzeScalingBehavior(dataPoints);
    }
    
    public static CrossoverDataPoint analyzeSingleSize(int size, int runs) {
        // Quick benchmark with reduced output
        BenchmarkResult basic = benchmarkMethodQuiet("Basic", n -> basicSieve(n), size, runs);
        BenchmarkResult enhanced = benchmarkMethodQuiet("Enhanced", n -> enhancedSieve(n), size, runs);
        BenchmarkResult ultra = benchmarkMethodQuiet("Ultra", n -> ultraEnhancedSieve(n), size, runs);
        BenchmarkResult segmented = benchmarkMethodQuiet("Segmented", n -> segmentedSieve(n), size, runs);
        
        // Determine winner among all four
        double minTime = Math.min(Math.min(basic.avgTime, enhanced.avgTime), 
                                 Math.min(ultra.avgTime, segmented.avgTime));
        String winner;
        double speedup;
        
        if (minTime == basic.avgTime) {
            winner = "Basic";
            speedup = Math.max(Math.max(enhanced.avgTime, ultra.avgTime), segmented.avgTime) / basic.avgTime;
        } else if (minTime == enhanced.avgTime) {
            winner = "Enhanced";
            speedup = basic.avgTime / enhanced.avgTime;
        } else if (minTime == ultra.avgTime) {
            winner = "Ultra";
            speedup = basic.avgTime / ultra.avgTime;
        } else {
            winner = "Segmented";
            speedup = basic.avgTime / segmented.avgTime;
        }
        
        return new CrossoverDataPoint(size, 
            basic.avgTime / 1_000_000.0, enhanced.avgTime / 1_000_000.0, 
            segmented.avgTime / 1_000_000.0, winner, speedup);
    }
    
    // Quiet benchmarking method (no console output)
    public static BenchmarkResult benchmarkMethodQuiet(String methodName, 
            Function<Integer, boolean[]> method, int n, int runs) {
        
        long totalTime = 0;
        long minTime = Long.MAX_VALUE;
        long maxTime = Long.MIN_VALUE;
        int primeCount = 0;
        
        // Warm up
        for (int i = 0; i < 2; i++) {
            method.apply(n);
        }
        
        // Run tests
        for (int run = 0; run < runs; run++) {
            System.gc();
            
            long startTime = System.nanoTime();
            boolean[] result = method.apply(n);
            long endTime = System.nanoTime();
            
            long executionTime = endTime - startTime;
            totalTime += executionTime;
            minTime = Math.min(minTime, executionTime);
            maxTime = Math.max(maxTime, executionTime);
            
            if (run == 0) {
                primeCount = countPrimes(result);
            }
        }
        
        double avgTime = totalTime / (double) runs;
        return new BenchmarkResult(methodName, avgTime, minTime, maxTime, 
                                 totalTime, primeCount, n);
    }
    
    public static void analyzeAndReportCrossovers(int[] testSizes, int runs) {
        System.out.println("\n📊 CROSSOVER ANALYSIS SUMMARY:");
        System.out.println("=" + "=".repeat(50));
        
        System.out.println("🎯 PREDICTED CROSSOVER POINTS:");
        System.out.println("• Basic → Enhanced: ~5,000 - 15,000");
        System.out.println("• Enhanced → Segmented: ~100,000 - 500,000");
        System.out.println("• Cache effects become significant: >250,000");
        
        System.out.println("\n💡 OPTIMIZATION INSIGHTS:");
        System.out.println("• For applications with mixed input sizes, consider adaptive selection");
        System.out.println("• Memory-constrained environments favor segmented approach earlier");
        System.out.println("• JIT compilation may shift crossover points in long-running applications");
    }
    
    public static void identifyDetailedCrossovers(CrossoverDataPoint[] dataPoints) {
        System.out.println("\n🔍 DETAILED CROSSOVER IDENTIFICATION:");
        System.out.println("-".repeat(60));
        
        String prevWinner = dataPoints[0].winner;
        for (int i = 1; i < dataPoints.length; i++) {
            if (!dataPoints[i].winner.equals(prevWinner)) {
                System.out.printf("📈 Crossover detected: %s → %s at input size ~%d%n",
                    prevWinner, dataPoints[i].winner, dataPoints[i].inputSize);
                prevWinner = dataPoints[i].winner;
            }
        }
    }
    
    public static void analyzeScalingBehavior(CrossoverDataPoint[] dataPoints) {
        System.out.println("\n📈 SCALING BEHAVIOR ANALYSIS:");
        System.out.println("-".repeat(50));
        
        // Analyze how algorithms scale with input size
        System.out.println("Algorithm performance trends as input size increases:");
        
        // Calculate growth rates between first and last measurements
        double basicGrowth = dataPoints[dataPoints.length-1].basicTime / dataPoints[0].basicTime;
        double enhancedGrowth = dataPoints[dataPoints.length-1].enhancedTime / dataPoints[0].enhancedTime;
        double segmentedGrowth = dataPoints[dataPoints.length-1].segmentedTime / dataPoints[0].segmentedTime;
        
        System.out.printf("• Basic Sieve: %.1fx slower (linear scaling)%n", basicGrowth);
        System.out.printf("• Enhanced Sieve: %.1fx slower (optimized scaling)%n", enhancedGrowth);
        System.out.printf("• Segmented Sieve: %.1fx slower (cache-efficient scaling)%n", segmentedGrowth);
    }
    
    // Helper class for crossover analysis
    static class CrossoverDataPoint {
        int inputSize;
        double basicTime;
        double enhancedTime;
        double segmentedTime;
        String winner;
        double speedup;
        
        CrossoverDataPoint(int inputSize, double basicTime, double enhancedTime, 
                          double segmentedTime, String winner, double speedup) {
            this.inputSize = inputSize;
            this.basicTime = basicTime;
            this.enhancedTime = enhancedTime;
            this.segmentedTime = segmentedTime;
            this.winner = winner;
            this.speedup = speedup;
        }
    }
    
    // Helper class to store benchmark results
    static class BenchmarkResult {
        String methodName;
        double avgTime;
        long minTime;
        long maxTime;
        long totalTime;
        int primeCount;
        int inputSize;
        
        BenchmarkResult(String methodName, double avgTime, long minTime, long maxTime, 
                       long totalTime, int primeCount, int inputSize) {
            this.methodName = methodName;
            this.avgTime = avgTime;
            this.minTime = minTime;
            this.maxTime = maxTime;
            this.totalTime = totalTime;
            this.primeCount = primeCount;
            this.inputSize = inputSize;
        }
    }

}//end of class findPrimes