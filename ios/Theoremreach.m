#import "Theoremreach.h"


bool isInitialized;
bool isInitializing;

@implementation RNTheoremreach

@synthesize bridge = _bridge;

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()


#pragma mark exported methods


// Initialize Theoremreach
RCT_EXPORT_METHOD(initialize :(NSString *)apiKey :(NSString *)userId)
{
  NSLog(@"initialize Theoremreach");
  [TheoremReach initWithApiKey: apiKey userId: userId];
}

RCT_EXPORT_METHOD(show)
{
    NSLog(@"show Theoremreach");
    [TheoremReach showRewardCenter];
}

RCT_EXPORT_METHOD(surveyAvailable)
{
    NSLog(@"isTheoremreachPresent");
    NSLog([TheoremReach isSurveyAvailable]?@"YES":@"NO");
    [TheoremReach isSurveyAvailable];
}

- (void)onRewardCenterOpened
{
    NSLog(@"Pollfish is opened!");
}

- (void)onRewardCenterClosed {
  // reward center opened! Back to the app to use our coins!
    NSLog(@"onRewardCenterClosed");
}

@end
