import SwiftUI
import shared

struct ContentView: View {
	@State var launches = [RocketLaunch]()
	let sdk = SpaceXSDK()


	var body: some View {
		List(launches, id: \.flightNumber) { item in
			VStack(alignment: .leading) {
				RocketLaunchRow(rocketLaunch: item)
			}
		}.onAppear(perform: loadData)

	}


	func loadData() {
		sdk.getLaunches(forceReload: true, completionHandler:
											{ launches, error in
												if let launches = launches {
													print("launches \(launches)")
													self.launches = launches
												} else {
													//													 selflaunches = .error(error?.localizedDescription ?? "error")
												}
											})

	}

}


extension RocketLaunch: Identifiable { }
